package com.reactive.services;

import com.reactive.models.Livro;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LivroService {

    Flux<Livro> findAll();

    Mono<Livro> findById(Long id);

    Mono<Livro> save(Livro livro);
}
