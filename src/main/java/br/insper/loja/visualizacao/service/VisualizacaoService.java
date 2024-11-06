package br.insper.loja.visualizacao.service;

import br.insper.loja.common.TokenUtils;
import br.insper.loja.visualizacao.dto.CriarVisualizacaoDTO;
import br.insper.loja.visualizacao.dto.RetornarVisualizacaoDTO;
import br.insper.loja.visualizacao.model.Visualizacao;
import br.insper.loja.visualizacao.repository.VisualizacaoRepository;
import br.insper.loja.usuario.model.Usuario;
import br.insper.loja.usuario.repository.UsuarioRepository;
import br.insper.loja.filme.model.Filme;
import br.insper.loja.filme.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisualizacaoService {

    @Autowired
    private VisualizacaoRepository visualizacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    public void registrarVisualizacao(CriarVisualizacaoDTO criarVisualizacaoDTO, String email) {
        Optional<Filme> filmeOpt = filmeRepository.findById(criarVisualizacaoDTO.getFilmeId());

        if (!email.isBlank()&& filmeOpt.isPresent()) {
            Visualizacao visualizacao = new Visualizacao();
            visualizacao.setEmail(email);
            visualizacao.setFilme(filmeOpt.get());
            visualizacao.setTempoAssistido(criarVisualizacaoDTO.getTempoAssistido());
            visualizacao.setDataVisualizacao(criarVisualizacaoDTO.getDataVisualizacao());

            visualizacaoRepository.save(visualizacao);
        } else {
            throw new IllegalArgumentException("Usuário ou filme não encontrado");
        }
    }

    public List<RetornarVisualizacaoDTO> listarHistorico(String email) {
        List<Visualizacao> visualizacoes = visualizacaoRepository.findByEmail(email);
        return visualizacoes.stream().map(v -> {
            RetornarVisualizacaoDTO dto = new RetornarVisualizacaoDTO();
            dto.setFilmeId(v.getFilme().getId());
            dto.setDataVisualizacao(v.getDataVisualizacao());
            dto.setTempoAssistido(v.getTempoAssistido());
            return dto;
        }).collect(Collectors.toList());
    }

    public String resumo(String email) {
        List<Visualizacao> visualizacoes = visualizacaoRepository.findByEmail(email);

        int tempoTotalAssistido = visualizacoes.stream()
                .mapToInt(Visualizacao::getTempoAssistido)
                .sum();

        Map<String, Long> generoFrequencia = visualizacoes.stream()
                .map(v -> v.getFilme().getGenero())
                .collect(Collectors.groupingBy(genero -> genero, Collectors.counting()));

        long maxFrequencia = generoFrequencia.values().stream().max(Long::compare).orElse(0L);
        List<String> generosMaisVisualizados = generoFrequencia.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequencia)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Formatar a resposta
        return String.format(
                "Tempo total assistido: %d minutos\nGêneros mais visualizados: %s",
                tempoTotalAssistido,
                String.join(", ", generosMaisVisualizados)
        );
    }
}
