package br.insper.loja.filme.controller;

import br.insper.loja.filme.model.Filme;
import br.insper.loja.filme.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<Filme>> listarTodosFilmes() {
        List<Filme> filmes = filmeService.listarTodosFilmes();
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Integer id) {
        Optional<Filme> filme = filmeService.buscarFilmePorId(id);
        return filme.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme) {
        Filme novoFilme = filmeService.criarFilme(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
}
