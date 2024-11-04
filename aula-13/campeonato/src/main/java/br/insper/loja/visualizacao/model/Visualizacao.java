package br.insper.loja.visualizacao.model;

import br.insper.loja.usuario.model.Usuario;
import br.insper.loja.filme.model.Filme;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visualizacoes")
public class Visualizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;

    private LocalDate dataVisualizacao;

    @Column(nullable = false)
    private int tempoAssistido;


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

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public LocalDate getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(LocalDate dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }

    public int getTempoAssistido() {
        return tempoAssistido;
    }

    public void setTempoAssistido(int tempoAssistido) {
        this.tempoAssistido = tempoAssistido;
    }
}
