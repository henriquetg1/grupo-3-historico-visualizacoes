package br.insper.loja.visualizacao.controller;

import br.insper.loja.common.TokenUtils;
import br.insper.loja.visualizacao.dto.CriarVisualizacaoDTO;
import br.insper.loja.visualizacao.dto.RetornarVisualizacaoDTO;
import br.insper.loja.visualizacao.service.VisualizacaoService;
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
    public ResponseEntity<Void> registrarVisualizacao(
            @RequestBody CriarVisualizacaoDTO criarVisualizacaoDTO,
            @RequestHeader(name = "Authorization") String auth
    ) {
        String email = TokenUtils.getEmailFromToken(auth);
        visualizacaoService.registrarVisualizacao(criarVisualizacaoDTO, email);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<RetornarVisualizacaoDTO>> listarHistorico(
            @RequestHeader(name = "Authorization") String auth
    ) {
        String email = TokenUtils.getEmailFromToken(auth);
        List<RetornarVisualizacaoDTO> historico = visualizacaoService.listarHistorico(email);
        return ResponseEntity.ok(historico);
    }

//    public String resumo(@RequestHeader(name = "Authorization") String auth) {
//        String email = TokenUtils.getEmailFromToken(auth);
//
//        return filmeService.resumo(email);
//    }
}
