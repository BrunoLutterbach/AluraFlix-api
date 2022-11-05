package br.com.brunolutterbach.aluraflix.controller;

import br.com.brunolutterbach.aluraflix.dto.VideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.VideoForm;
import br.com.brunolutterbach.aluraflix.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<VideoForm> cadastrarVideo(@RequestBody @Valid VideoForm videoForm){
        return videoService.cadastrarVideo(videoForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoForm> atualizarVideo(@PathVariable Long id, @RequestBody @Valid VideoForm videoForm) {
        return videoService.atualizarVideo(id, videoForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVideo(@PathVariable Long id) {
        return videoService.deletarVideo(id);
    }

}
