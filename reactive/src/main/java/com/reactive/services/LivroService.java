package com.reactive.services;

import com.reactive.models.Livro;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface LivroService {

    Flux<Livro> findAll();

    Mono<Livro> findById(String id);

    Mono<Livro> save(Livro livro);
}
