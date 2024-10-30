package br.insper.loja.filme.controller;

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
    public Filme salvarFilme(@RequestBody Usuario usuario, @RequestBody Filme filme) {
        return filmeService.salvarFilme(usuario, filme);
    }

    @GetMapping
    public List<Filme> listarFilmes(@RequestBody Usuario usuario, @RequestParam(required = false) LocalDateTime data, @RequestParam(required = false) String nome, @RequestParam(required = false) String genero) {
        return filmeService.listarFilmes(usuario, data, nome, genero);
    }

    @GetMapping("/resumo")
    public String resumo(@RequestBody Usuario usuario) {
        return filmeService.resumo(usuario);
    }
}
