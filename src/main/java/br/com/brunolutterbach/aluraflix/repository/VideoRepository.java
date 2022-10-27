package br.com.brunolutterbach.aluraflix.repository;

import br.com.brunolutterbach.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {



}
