package br.com.brunolutterbach.aluraflix.dto;

import br.com.brunolutterbach.aluraflix.model.Video;
import lombok.Data;
import org.springframework.data.domain.Page;


@Data
public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public static Page<VideoDto> converter(Page<Video> videos) {
        return videos.map(VideoDto::new);
    }
}
