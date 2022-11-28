package br.com.brunolutterbach.aluraflix.service;

import br.com.brunolutterbach.aluraflix.dto.VideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.VideoForm;
import br.com.brunolutterbach.aluraflix.dto.form.VideoUpdateForm;
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

import java.util.Objects;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class VideoService {

    final VideoRepository videoRepository;
    final CategoriaRepository categoriaRepository;

    public ResponseEntity<Page<VideoDto>> listarTodos(@PageableDefault(sort = "id", direction = ASC)
                                                      Pageable pageable, String titulo) {
        if (titulo == null) {
            return ResponseEntity.ok(videoRepository.findAllByAtivoTrue(pageable).map(VideoDto::new));
        }
        return ResponseEntity.ok(videoRepository.listarPorTitulo(titulo, pageable).map(VideoDto::new));
    }

    public ResponseEntity<VideoDto> buscarPorId(Long id) {
        return videoRepository.findById(id).map(video -> ResponseEntity.ok(new VideoDto(video)))
                .orElseThrow(() -> new ResourceNotFoundException("Video de id " + id + " não encontrado"));
    }

    public ResponseEntity<VideoForm> cadastrarVideo(VideoForm videoForm) {
        videoRepository.save(new Video(videoForm, categoriaRepository));
        return ResponseEntity.ok(videoForm);
    }

    public ResponseEntity<VideoDto> atualizarVideo(Long id, VideoUpdateForm videoUpdateForm) {
        var video = videoRepository.getReferenceById(id);
        var categoria = categoriaRepository.getReferenceById(Objects.requireNonNullElse(videoUpdateForm.categoriaId(), video.getCategoria().getId()));
        return ResponseEntity.ok(video.atualizar(videoUpdateForm, categoria));
    }

    public ResponseEntity<?> deletarVideo(Long id) {
        var video = videoRepository.getReferenceById(id);
        video.inativar();
        return ResponseEntity.noContent().build();
    }
}



