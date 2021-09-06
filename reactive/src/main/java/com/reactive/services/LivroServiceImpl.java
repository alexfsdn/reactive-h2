package com.reactive.services;

import com.reactive.models.Livro;
import com.reactive.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public Flux<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public Mono<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    @Override
    public Mono<Livro> save(Livro livro) {
        return livroRepository.save(livro);
    }
}
