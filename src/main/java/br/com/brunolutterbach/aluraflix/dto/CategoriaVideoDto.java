package br.com.brunolutterbach.aluraflix.dto;

import br.com.brunolutterbach.aluraflix.model.Categoria;
import br.com.brunolutterbach.aluraflix.repository.VideoRepository;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaVideoDto {

    private Long id;
    private String titulo;
    private String cor;
    private List<VideoDto> videos;

    public CategoriaVideoDto(Categoria categoriaVideoDto, VideoRepository videoRepository) {
        this.id = categoriaVideoDto.getId();
        this.titulo = categoriaVideoDto.getTitulo();
        this.cor = categoriaVideoDto.getCor();
        this.videos = VideoDto.converter(videoRepository.findByCategoriaId(categoriaVideoDto.getId()));
    }
}
