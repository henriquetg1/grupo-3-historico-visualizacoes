package br.insper.loja.visualizacao.controller;

import br.insper.loja.visualizacao.service.VisualizacaoService;
import br.insper.loja.visualizacao.dto.CriarVisualizacaoDTO;
import br.insper.loja.visualizacao.dto.RetornarVisualizacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visualizacoes")
public class VisualizacaoController {

    @Autowired
    private VisualizacaoService visualizacaoService;

    @PostMapping
    public ResponseEntity<Void> registrarVisualizacao(@RequestBody CriarVisualizacaoDTO criarVisualizacaoDTO) {
        visualizacaoService.registrarVisualizacao(criarVisualizacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<RetornarVisualizacaoDTO>> listarHistorico(@RequestParam String email) {
        List<RetornarVisualizacaoDTO> historico = visualizacaoService.listarHistorico(email);
        return ResponseEntity.ok(historico);
    }
}
