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

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Filme salvarFilme(Usuario user, Filme filme) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getEmail());
        if (usuario.isPresent()) {
            List<Filme> hist = usuario.get().getHistorico();
            hist.add(filme);
            usuario.get().setHistorico(hist);
            return filme;
        }
        return null;
    }

    public List<Filme> listarFilmes(Usuario user, LocalDateTime data, String nome, String genero) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getEmail());
        if (usuario.isPresent()) {
            List<Filme> historico = usuario.get().getHistorico();
            List<Filme> response = new ArrayList<>();

            for (Filme filme : historico) {
                boolean matches = true;

                if (data != null && !filme.getData().equals(data)) {
                    matches = false;
                }
                if (nome != null && !filme.getNome().equalsIgnoreCase(nome)) {
                    matches = false;
                }
                if (genero != null && !filme.getGenero().equalsIgnoreCase(genero)) {
                    matches = false;
                }

                if (matches) {
                    response.add(filme);
                }
            }

            return response;
        }
        return null;
    }

    public String resumo(Usuario user) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getEmail());
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