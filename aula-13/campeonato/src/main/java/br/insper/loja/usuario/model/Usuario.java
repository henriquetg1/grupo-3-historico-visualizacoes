package br.insper.loja.usuario.model;

import br.insper.loja.visualizacao.model.Visualizacao;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visualizacao> historico = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Visualizacao> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Visualizacao> historico) {
        this.historico = historico;
    }
}