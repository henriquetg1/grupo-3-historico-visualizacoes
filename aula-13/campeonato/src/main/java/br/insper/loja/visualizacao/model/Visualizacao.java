package br.insper.loja.visualizacao.model;

import br.insper.loja.usuario.model.Usuario;
import jakarta.persistence.*;

@Entity
public class Visualizacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "filme_id")
	private Filme filme;

	private LocalDate dataVisualizacao;
	private int tempoAssistido;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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