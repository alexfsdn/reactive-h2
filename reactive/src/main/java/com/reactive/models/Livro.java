package com.reactive.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private boolean capaDura;
    private int numeroPaginas;
    private String nomeDaEditora;

    public Livro() {

    }

    public Livro(String nome, boolean capaDura, int numeroPaginas, String nomeDaEditora) {
        this.nome = nome;
        this.capaDura = capaDura;
        this.numeroPaginas = numeroPaginas;
        this.nomeDaEditora = nomeDaEditora;
    }
}