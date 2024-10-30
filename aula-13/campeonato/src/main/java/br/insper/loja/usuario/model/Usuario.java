package br.insper.loja.usuario.model;

import java.util.ArrayList;
import java.util.List;

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

    // MANY TO SOMETHING???
    private List<Filme> historico = new ArrayList<>();

    public List<Filme> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Filme> historico) {
        this.historico = historico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
