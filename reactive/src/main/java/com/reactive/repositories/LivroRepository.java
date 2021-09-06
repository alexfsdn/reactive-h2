package com.reactive.repositories;

import com.reactive.models.Livro;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends R2dbcRepository<Livro, Long> {
}
