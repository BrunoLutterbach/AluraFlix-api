package br.com.brunolutterbach.aluraflix.controller;

import br.com.brunolutterbach.aluraflix.dto.CategoriaDto;
import br.com.brunolutterbach.aluraflix.dto.CategoriaVideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaForm;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaUpdateForm;
import br.com.brunolutterbach.aluraflix.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

    final CategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<Page<CategoriaDto>> listarCategorias(Pageable pageable){
        Page<CategoriaDto> categoriaDtos = categoriaService.listarTodos(pageable);
        return ResponseEntity.ok(categoriaDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable Long id){
        CategoriaDto categoriaDto = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoriaDto);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<CategoriaVideoDto> buscarCategoriaPorIdComVideos(@PathVariable Long id){
        CategoriaVideoDto categoriaVideoDto = categoriaService.buscarPorIdComVideos(id);
        return ResponseEntity.ok(categoriaVideoDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaForm> cadastrarCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        CategoriaForm categoriaCadastrada = categoriaService.cadastrarCategoria(categoriaForm);
        return ResponseEntity.ok(categoriaCadastrada);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaUpdateForm categoriaUpdateForm) {
        CategoriaDto categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaUpdateForm);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
