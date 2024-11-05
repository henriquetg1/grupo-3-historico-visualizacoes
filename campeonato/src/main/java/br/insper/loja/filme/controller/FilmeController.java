package br.insper.loja.filme.controller;

import br.insper.loja.common.TokenUtils;
import br.insper.loja.filme.model.Filme;
import br.insper.loja.filme.service.FilmeService;
import br.insper.loja.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    public Filme salvarFilme(@RequestBody Filme filme,
                             @RequestHeader(name = "Authorization") String auth) {
        String email = TokenUtils.getEmailFromToken(auth);

        return filmeService.salvarFilme(email, filme);
    }

    @GetMapping
    public List<Filme> listarFilmes(@RequestHeader(name = "Authorization") String auth, @RequestParam(required = false) LocalDateTime data, @RequestParam(required = false) String nome, @RequestParam(required = false) String genero) {
        String email = TokenUtils.getEmailFromToken(auth);

        return filmeService.listarFilmes(email, data, nome, genero);
    }

    @GetMapping("/resumo")
    public String resumo(@RequestHeader(name = "Authorization") String auth) {
        String email = TokenUtils.getEmailFromToken(auth);

        return filmeService.resumo(email);
    }
}
