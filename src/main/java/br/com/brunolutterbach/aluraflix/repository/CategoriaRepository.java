package br.com.brunolutterbach.aluraflix.repository;

import br.com.brunolutterbach.aluraflix.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

