package com.reactive.controllers;

import com.reactive.models.Livro;
import com.reactive.services.LivroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class LivroController {

    @Autowired
    LivroServiceImpl livroService;

    @GetMapping(value = "/livros")
    public Flux<Livro> find() {
        return livroService.findAll();
    }

    @GetMapping(value = "/livros/{id}")
    public Mono<Livro> find(@PathVariable String id) {
        return livroService.findById(id);
    }


    @PostMapping(value = "/livros")
    public Mono<Livro> save(@RequestBody Livro livro) {
        return livroService.save(livro);
    }

    @GetMapping(value = "/playlist/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Livro>> findLivrosWebFlux() {

        System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Livro> livroFlux = livroService.findAll();

        return Flux.zip(interval, livroFlux);

    }


}
