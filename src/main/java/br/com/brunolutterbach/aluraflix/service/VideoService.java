package br.com.brunolutterbach.aluraflix.service;

import br.com.brunolutterbach.aluraflix.dto.VideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.VideoForm;
import br.com.brunolutterbach.aluraflix.exception.ResourceNotFoundException;
import br.com.brunolutterbach.aluraflix.model.Video;
import br.com.brunolutterbach.aluraflix.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.*;

@Service
@AllArgsConstructor
public class VideoService {

    final VideoRepository videoRepository;

    public ResponseEntity<Page<VideoDto>> listarVideos(@PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
        Page<Video> videos = videoRepository.findAll(pageable);
        return ResponseEntity.ok(VideoDto.converter(videos));
    }

    public ResponseEntity<VideoDto> listarVideoPorId(Long id) {
        var video = videoRepository.findById(id);
        return video.map(videoDto -> ResponseEntity.ok(new VideoDto(videoDto)))
                .orElseThrow(() -> new ResourceNotFoundException("Video de id " + id + " não foi encontrado"));
    }

    public ResponseEntity<VideoForm> cadastrarVideo(@Valid VideoForm videoForm) {
        var video = videoForm.converter(new Video());
        videoRepository.save(video);
        return ResponseEntity.ok(videoForm);
    }

    public ResponseEntity<VideoForm> atualizarVideo(@Valid Long id, VideoForm videoForm) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            Video videoAtualizado = videoForm.atualizar(id, videoRepository);
            videoRepository.save(videoAtualizado);
            return ResponseEntity.ok(videoForm);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deletarVideo(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            videoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}



