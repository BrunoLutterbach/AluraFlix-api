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
       return categoriaService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable Long id){
        return categoriaService.buscarPorId(id);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<CategoriaVideoDto> buscarCategoriaPorIdComVideos(@PathVariable Long id){
        return categoriaService.buscarPorIdComVideos(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaForm> cadastrarCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        return categoriaService.cadastrarCategoria(categoriaForm);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaUpdateForm categoriaUpdateForm) {
        return categoriaService.atualizarCategoria(id, categoriaUpdateForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
        return categoriaService.deletarCategoria(id);
    }




}
