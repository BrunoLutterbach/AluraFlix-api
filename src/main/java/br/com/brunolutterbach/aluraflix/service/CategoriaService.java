package br.com.brunolutterbach.aluraflix.service;

import br.com.brunolutterbach.aluraflix.dto.CategoriaDto;
import br.com.brunolutterbach.aluraflix.dto.CategoriaVideoDto;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaForm;
import br.com.brunolutterbach.aluraflix.dto.form.CategoriaUpdateForm;
import br.com.brunolutterbach.aluraflix.exception.ResourceNotFoundException;
import br.com.brunolutterbach.aluraflix.model.Categoria;
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
public class CategoriaService {

    final CategoriaRepository categoriaRepository;
    final VideoRepository videoRepository;

    public ResponseEntity<Page<CategoriaDto>> listarTodos(@PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
        return ResponseEntity.ok(categoriaRepository.findAll(pageable).map(CategoriaDto::new));
    }

    public ResponseEntity<CategoriaDto> buscarPorId(Long id) {
        return ResponseEntity.ok(categoriaRepository.findById(id).map(CategoriaDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de id " + id + " não encontrada")));
    }

    public ResponseEntity<CategoriaVideoDto> buscarPorIdComVideos(Long id) {
        return ResponseEntity.ok(categoriaRepository.findById(id).map(categoria -> new CategoriaVideoDto(categoria, videoRepository))
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de id " + id + " não encontrada")));
    }

    public ResponseEntity<CategoriaForm> cadastrarCategoria(CategoriaForm categoriaForm) {
        categoriaRepository.save(new Categoria(categoriaForm));
        return ResponseEntity.ok(categoriaForm);
    }

    public ResponseEntity<CategoriaDto> atualizarCategoria(Long id, CategoriaUpdateForm categoriaUpdateForm) {
        var categoria = categoriaRepository.getReferenceById(id);
        return ResponseEntity.ok(categoria.atualizar(categoriaUpdateForm));
    }

    public ResponseEntity<?> deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
