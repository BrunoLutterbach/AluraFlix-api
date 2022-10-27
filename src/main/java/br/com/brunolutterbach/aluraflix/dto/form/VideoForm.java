package br.com.brunolutterbach.aluraflix.dto.form;

import br.com.brunolutterbach.aluraflix.model.Video;
import br.com.brunolutterbach.aluraflix.repository.VideoRepository;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VideoForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    private String url;

    public Video converter(Video video) {
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setUrl(url);
        return video;
    }

    public Video atualizar(Long id, VideoRepository videoRepository) {
        Video video = videoRepository.getReferenceById(id);
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setUrl(url);
        return video;
    }

}
