package com.reactive.repository;

import com.reactive.models.Livro;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LivroRepository extends ReactiveCrudRepository<Livro, String> {
}
