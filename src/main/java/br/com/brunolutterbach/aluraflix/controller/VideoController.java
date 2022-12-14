package br.com.brunolutterbach.aluraflix.controller;

import br.com.brunolutterbach.aluraflix.dto.VideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.VideoForm;
import br.com.brunolutterbach.aluraflix.dto.form.VideoUpdateForm;
import br.com.brunolutterbach.aluraflix.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/videos")
@AllArgsConstructor
public class VideoController {

    final VideoService videoService;

    @GetMapping()
    public ResponseEntity<Page<VideoDto>> listar(@RequestParam(required = false) String titulo, Pageable pageable) {
        Page<VideoDto> videoDtos = videoService.listarTodos(pageable, titulo);
        return ResponseEntity.ok(videoDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVideoPorId(@PathVariable Long id){
        VideoDto videoDto = videoService.buscarPorId(id);
        return ResponseEntity.ok(videoDto);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<VideoForm> cadastrarVideo(@RequestBody @Valid VideoForm videoForm){
        VideoForm videoCadastrado = videoService.cadastrarVideo(videoForm);
        return ResponseEntity.ok(videoCadastrado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizarVideo(@PathVariable Long id, @RequestBody @Valid VideoUpdateForm videoUpdateForm) {
        VideoDto videoAtualizado = videoService.atualizarVideo(id, videoUpdateForm);
        return ResponseEntity.ok().body(videoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativarVideo(@PathVariable Long id) {
        videoService.inativarVideo(id);
        return ResponseEntity.noContent().build();
    }
}
