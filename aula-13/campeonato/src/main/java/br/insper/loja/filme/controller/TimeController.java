package br.insper.loja.filme.controller;

import br.insper.loja.partida.service.PartidaService;
import br.insper.loja.filme.service.TimeService;
import br.insper.loja.filme.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeController {

    @Autowired
    private TimeService timeService;

    @Autowired
    private PartidaService partidaService;


}
