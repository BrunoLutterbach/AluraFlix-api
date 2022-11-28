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
        return videoService.listarTodos(pageable, titulo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVideoPorId(@PathVariable Long id){
        return videoService.buscarPorId(id);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<VideoForm> cadastrarVideo(@RequestBody @Valid VideoForm videoForm){
        return videoService.cadastrarVideo(videoForm);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizarVideo(@PathVariable Long id, @RequestBody @Valid VideoUpdateForm videoUpdateForm) {
        return videoService.atualizarVideo(id, videoUpdateForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVideo(@PathVariable Long id) {
        return videoService.deletarVideo(id);
    }

}
