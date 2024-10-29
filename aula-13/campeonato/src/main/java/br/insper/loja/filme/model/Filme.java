package br.insper.loja.filme.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private LocalDateTime data;
    
    @Column(nullable = false)
    private String tempo;


    public Filme() {
    }

    public Filme(Integer id, String nome, String genero, LocalDateTime data) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
