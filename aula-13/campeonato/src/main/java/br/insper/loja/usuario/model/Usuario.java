package br.insper.loja.usuario.model;

import java.util.ArrayList;

import br.insper.loja.filme.model.Filme;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    // one-to-many
    private ArrayList<Visualizacao> historico = new ArrayList<>();

}
