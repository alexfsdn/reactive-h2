package com.reactive.config;

import com.reactive.models.Livro;
import com.reactive.repository.LivroRepository;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Flux;

import java.util.UUID;
import java.util.stream.Stream;

@Configuration
@EnableR2dbcRepositories
class R2DBCConfiguration extends AbstractR2dbcConfiguration {

    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url("r2dbc:h2:mem:default;DB_CLOSE_DELAY=-1;")
                        .username("sa")
                        .build()
        );
    }

    @Bean
    ApplicationRunner init(LivroRepository repository, DatabaseClient client) {
        return args -> {
            client.execute("create table IF NOT EXISTS LIVRO" +
                    "(id varchar(255) PRIMARY KEY, nome varchar (255) not null, capaDura bit, numeroPaginas int, nome_da_editora varchar(255));").fetch().first().subscribe();
            client.execute("DELETE FROM LIVRO;").fetch().first().subscribe();

            Livro livro = new Livro(UUID.randomUUID(), "Livro 1", true, 200, "Editora 1");
            Livro livro2 = new Livro(UUID.randomUUID(), "Livro 2", true, 200, "Editora 2");
            Livro livro3 = new Livro(UUID.randomUUID(), "Livro 3", true, 200, "Editora 3");
            Livro livro4 = new Livro(UUID.randomUUID(), "Livro 4", true, 200, "Editora 4");
            Livro livro5 = new Livro(UUID.randomUUID(), "Livro 5", true, 200, "Editora 5");

            Stream<Livro> stream = Stream.of(livro, livro2, livro3, livro4, livro5);

            // initialize the database

            repository.saveAll(Flux.fromStream(stream))
                    .then()
                    .subscribe(); // execute

        };
    }

}
