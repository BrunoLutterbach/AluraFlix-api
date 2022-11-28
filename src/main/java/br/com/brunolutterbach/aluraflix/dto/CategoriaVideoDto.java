package br.com.brunolutterbach.aluraflix.dto;

import br.com.brunolutterbach.aluraflix.model.Categoria;
import br.com.brunolutterbach.aluraflix.repository.VideoRepository;

import java.util.List;

public record CategoriaVideoDto(Long id, String titulo, String cor, List<VideoDto> videos) {

    public CategoriaVideoDto(Categoria categoria, VideoRepository videoRepository) {
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor(),
                videoRepository.findByCategoriaId(categoria.getId()).stream().map(VideoDto::new).toList());
                // Para cada video, cria um VideoDto e adiciona na lista
    }
}

