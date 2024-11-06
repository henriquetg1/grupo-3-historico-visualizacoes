package br.insper.loja.visualizacao.repository;

import br.insper.loja.visualizacao.model.Visualizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisualizacaoRepository extends JpaRepository<Visualizacao, Integer> {
    List<Visualizacao> findByEmail(String email);
}
