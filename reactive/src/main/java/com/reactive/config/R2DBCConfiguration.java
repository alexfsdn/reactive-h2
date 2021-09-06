package com.reactive.config;

import com.reactive.models.Livro;
import com.reactive.repositories.LivroRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.stream.Stream;

@Configuration
public class R2dbcConfiguration {

    @Bean
    ApplicationRunner init(LivroRepository repository, DatabaseClient client) {
        return args -> {
            client.sql("create table IF NOT EXISTS LIVRO" +
                    "(id INT IDENTITY PRIMARY KEY, nome varchar (255) not null, capa_dura bit, numero_paginas int, nome_da_editora varchar(255));").fetch().first().subscribe();
            client.sql("DELETE FROM LIVRO;").fetch().first().subscribe();

            Livro livro = new Livro("Livro 1", true, 200, "Editora 1");
            Livro livro2 = new Livro("Livro 2", true, 200, "Editora 2");
            Livro livro3 = new Livro("Livro 3", true, 200, "Editora 3");
            Livro livro4 = new Livro("Livro 4", true, 200, "Editora 4");
            Livro livro5 = new Livro("Livro 5", true, 200, "Editora 5");

            Stream<Livro> stream = Stream.of(livro, livro2, livro3, livro4, livro5);

            // initialize the database
            repository.save(livro).then().subscribe();
            repository.save(livro2).then().subscribe();
            repository.save(livro3).then().subscribe();
            repository.save(livro4).then().subscribe();
            repository.save(livro5).then().subscribe();

            /*
            repository.saveAll(Flux.fromStream(stream))
                    .then()
                    .subscribe(); // execute

             */
        };
    }
}
