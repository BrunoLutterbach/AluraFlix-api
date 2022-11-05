package br.com.brunolutterbach.aluraflix.service;

import br.com.brunolutterbach.aluraflix.dto.VideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.VideoForm;
import br.com.brunolutterbach.aluraflix.exception.ResourceNotFoundException;
import br.com.brunolutterbach.aluraflix.model.Video;
import br.com.brunolutterbach.aluraflix.repository.CategoriaRepository;
import br.com.brunolutterbach.aluraflix.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class VideoService {

    final VideoRepository videoRepository;
    final CategoriaRepository categoriaRepository;

    public ResponseEntity<Page<VideoDto>> listarTodos(@PageableDefault(sort = "id", direction = ASC)
                                                      Pageable pageable, String titulo) {
        if (titulo == null) {
            var videos = videoRepository.findAll(pageable);
            return ResponseEntity.ok(VideoDto.converter(videos));
        }
        var videos = videoRepository.listarPorTitulo(titulo, pageable);
        return ResponseEntity.ok(VideoDto.converter(videos));
    }

    public ResponseEntity<VideoDto> buscarPorId(Long id) {
        var video = videoRepository.findById(id);
        return video.map(videoDto -> ResponseEntity.ok(new VideoDto(videoDto)))
                .orElseThrow(() -> new ResourceNotFoundException("Video de id " + id + " não foi encontrado"));
    }

    public ResponseEntity<VideoForm> cadastrarVideo(VideoForm videoForm) {
        var video = videoForm.converter(new Video(), categoriaRepository);
        videoRepository.save(video);
        return ResponseEntity.ok(videoForm);
    }

    public ResponseEntity<VideoForm> atualizarVideo(Long id, VideoForm videoForm) {
        var video = videoRepository.findById(id);
        if (video.isPresent()) {
            var videoAtualizado = videoForm.atualizar(id, videoRepository);
            videoRepository.save(videoAtualizado);
            return ResponseEntity.ok(videoForm);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deletarVideo(Long id) {
        var video = videoRepository.findById(id);
        if (video.isPresent()) {
            videoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}



