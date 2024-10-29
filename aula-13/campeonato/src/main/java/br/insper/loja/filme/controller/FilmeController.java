package br.insper.loja.filme.controller;

import br.insper.loja.filme.repository.FilmeRepository;
import br.insper.loja.partida.service.PartidaService;
import br.insper.loja.filme.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private FilmeRepository filmeRepository;
}
