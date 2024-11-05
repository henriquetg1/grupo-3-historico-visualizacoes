package br.insper.loja.filme.service;

import br.insper.loja.filme.exception.TimeNaoEncontradoException;
import br.insper.loja.filme.model.Filme;
import br.insper.loja.filme.repository.FilmeRepository;
import br.insper.loja.usuario.model.Usuario;
import br.insper.loja.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Filme salvarFilme(String email, Filme filme) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            List<Filme> hist = usuario.get().getHistorico();
            hist.add(filme);
            usuario.get().setHistorico(hist);
            return filme;
        }
        return null;
    }

    public List<Filme> listarFilmes(String email, LocalDateTime data, String nome, String genero) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            List<Filme> historico = usuario.get().getHistorico();
            ArrayList<Filme> response = new ArrayList<>();

            Stream<Filme> s = response.stream();

            if (data != null) {
                s.filter(filme -> filme.getData().equals(data));
            }

            if (nome != null) {
                s.filter(filme -> filme.getNome().equals(nome));
            }

            if (genero != null) {
                s.filter(filme -> filme.getGenero().equals(genero));
            }

            return s.toList();
        }
        return null;
    }

    public String resumo(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            List<Filme> historico = usuario.get().getHistorico();
            String resumo = "";
            int tempo = 0;
            ArrayList<String> generos = new ArrayList<>();
            for (Filme filme : historico) {
                tempo += Integer.parseInt(filme.getTempo());
                if (!generos.contains(filme.getGenero())) {
                    generos.add(filme.getGenero());
                }
            }
            resumo += "Tempo total assistido: " + tempo + " minutos\n";
            resumo += "Gêneros assistidos: ";
            for (String genero : generos) {
                resumo += genero + ", ";
            }
            return resumo;
        }
        return null;
    }
}