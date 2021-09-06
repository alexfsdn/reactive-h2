package com.reactive.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_LIVRO")
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
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