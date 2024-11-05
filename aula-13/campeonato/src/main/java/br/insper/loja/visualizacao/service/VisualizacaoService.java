package br.insper.loja.visualizacao.service;

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
import java.time.LocalDate;
import java.util.List;
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

    public void registrarVisualizacao(CriarVisualizacaoDTO criarVisualizacaoDTO) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(criarVisualizacaoDTO.getUserEmail());
        Optional<Filme> filmeOpt = filmeRepository.findById(criarVisualizacaoDTO.getFilmeId());

        if (usuarioOpt.isPresent() && filmeOpt.isPresent()) {
            Visualizacao visualizacao = new Visualizacao();
            visualizacao.setUsuario(usuarioOpt.get());
            visualizacao.setFilme(filmeOpt.get());
            visualizacao.setTempoAssistido(criarVisualizacaoDTO.getTempoAssistido());
            visualizacao.setDataVisualizacao(criarVisualizacaoDTO.getDataVisualizacao());

            visualizacaoRepository.save(visualizacao);
        } else {
            throw new IllegalArgumentException("Usuário ou filme não encontrado");
        }
    }

    public List<RetornarVisualizacaoDTO> listarHistorico(String email) {
        List<Visualizacao> visualizacoes = visualizacaoRepository.findByUsuarioEmail(email);
        return visualizacoes.stream().map(v -> {
            RetornarVisualizacaoDTO dto = new RetornarVisualizacaoDTO();
            dto.setFilmeId(v.getFilme().getId());
            dto.setDataVisualizacao(v.getDataVisualizacao());
            dto.setTempoAssistido(v.getTempoAssistido());
            return dto;
        }).collect(Collectors.toList());
    }
}
