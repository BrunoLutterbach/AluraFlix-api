package br.com.brunolutterbach.aluraflix.dto;

import br.com.brunolutterbach.aluraflix.model.Video;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private Long categoriaId;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoriaId = video.getCategoria().getId();
    }

    public static Page<VideoDto> converter(Page<Video> videos) {
        return videos.map(VideoDto::new);
    }

    public static List<VideoDto> converter(List<Video> videos) {
        return videos.stream().map(VideoDto::new).toList();
    }
}
