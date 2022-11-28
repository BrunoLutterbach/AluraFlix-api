package br.com.brunolutterbach.aluraflix.repository;

import br.com.brunolutterbach.aluraflix.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {


    List<Video> findByCategoriaId(Long id);

    @Query("SELECT v FROM Video v WHERE v.titulo LIKE %:titulo%")
    Page<Video> listarPorTitulo(@Param("titulo") String titulo, Pageable paginacao);

    Page<Video> findAllByAtivoTrue(Pageable pageable);
}
