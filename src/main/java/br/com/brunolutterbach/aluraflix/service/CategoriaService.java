package br.com.brunolutterbach.aluraflix.service;

import br.com.brunolutterbach.aluraflix.dto.CategoriaDto;
import br.com.brunolutterbach.aluraflix.dto.CategoriaVideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaForm;
import br.com.brunolutterbach.aluraflix.exception.ResourceNotFoundException;
import br.com.brunolutterbach.aluraflix.model.Categoria;
import br.com.brunolutterbach.aluraflix.repository.CategoriaRepository;
import br.com.brunolutterbach.aluraflix.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    final CategoriaRepository categoriaRepository;
    final VideoRepository videoRepository;

    public ResponseEntity<List<CategoriaDto>> listarTodos() {
        var categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(CategoriaDto.converter(categorias));
    }

    public ResponseEntity<CategoriaDto> buscarPorId(Long id) {
        var categoria = categoriaRepository.findById(id);
        return categoria.map(categoriaDto -> ResponseEntity.ok(new CategoriaDto(categoriaDto)))
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de id " + id + " não foi encontrada"));
    }

    public ResponseEntity<CategoriaVideoDto> buscarPorIdComVideos(Long id) {
        var categoria = categoriaRepository.findById(id);
        return categoria.map(categoriaVideoDto -> ResponseEntity.ok(new CategoriaVideoDto(categoriaVideoDto, videoRepository)))
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de id " + id + " não foi encontrada"));
    }

    public ResponseEntity<CategoriaForm> cadastrarCategoria(CategoriaForm categoriaForm) {
        var categoria = categoriaForm.converter(new Categoria());
        categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoriaForm);
    }

    public ResponseEntity<CategoriaForm> atualizarCategoria(Long id, CategoriaForm categoriaForm) {
        var categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            var categoriaAtualizado = categoriaForm.atualizar(id, categoriaRepository);
            categoriaRepository.save(categoriaAtualizado);
            return ResponseEntity.ok(categoriaForm);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deletarCategoria(Long id) {
        var categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
