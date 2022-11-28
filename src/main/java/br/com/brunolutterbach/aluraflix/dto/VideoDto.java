package br.com.brunolutterbach.aluraflix.dto;

import br.com.brunolutterbach.aluraflix.model.Video;

public record VideoDto(Long id, String titulo, String descricao, String url, Long categoriaId) {

    public VideoDto(Video video) {
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getId());
    }
}
