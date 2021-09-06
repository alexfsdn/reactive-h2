package com.reactive.controllers;

import com.reactive.dtos.LivroDto;
import com.reactive.models.Livro;
import com.reactive.services.LivroServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class LivroController {

    @Autowired
    private LivroServiceImpl livroService;

    @GetMapping(value = "/livros", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Livro> find() {
        return livroService.findAll();
    }

    @GetMapping(value = "/livros/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Livro> find(@PathVariable Long id) {
        return livroService.findById(id);
    }


    @PostMapping(value = "/livros", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Livro> save(@RequestBody LivroDto livroDto) {
        Livro livro = new Livro();

        BeanUtils.copyProperties(livroDto, livro);
        return livroService.save(livro);
    }

    @GetMapping(value = "/livros/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Livro>> findLivrosWebFlux() {

        System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Livro> livroFlux = livroService.findAll();

        return Flux.zip(interval, livroFlux);

    }

}
