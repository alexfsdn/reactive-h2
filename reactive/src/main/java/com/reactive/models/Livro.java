package com.reactive.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.util.UUID;

@AllArgsConstructor
@Data
@Table
public class Livro {

    @Id
    private UUID id;
    private String nome;
    private boolean capaDura;
    private int numeroPaginas;
    private String nomeDaEditora;




}