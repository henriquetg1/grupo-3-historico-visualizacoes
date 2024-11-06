package br.insper.loja.visualizacao.dto;

import java.time.LocalDate;

public class CriarVisualizacaoDTO {
    private String email;
    private Integer filmeId;
    private int tempoAssistido;
    private LocalDate dataVisualizacao;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Integer filmeId) {
        this.filmeId = filmeId;
    }

    public int getTempoAssistido() {
        return tempoAssistido;
    }

    public void setTempoAssistido(int tempoAssistido) {
        this.tempoAssistido = tempoAssistido;
    }

    public LocalDate getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(LocalDate dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }
}
