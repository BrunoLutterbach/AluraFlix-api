package br.com.brunolutterbach.aluraflix.controller;

import br.com.brunolutterbach.aluraflix.dto.CategoriaDto;
import br.com.brunolutterbach.aluraflix.dto.CategoriaVideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaForm;
import br.com.brunolutterbach.aluraflix.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

    final CategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<List<CategoriaDto>> listarCategorias(){
       return categoriaService.listarTodos();
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
    public ResponseEntity<CategoriaForm> cadastrarCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        return categoriaService.cadastrarCategoria(categoriaForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaForm> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaForm categoriaForm) {
        return categoriaService.atualizarCategoria(id, categoriaForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
        return categoriaService.deletarCategoria(id);
    }




}
