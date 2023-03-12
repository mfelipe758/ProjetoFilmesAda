package br.com.ada.movieshdfree.controller;

import br.com.ada.movieshdfree.dao.FilmeDAO;
import br.com.ada.movieshdfree.model.Filme;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeDAO filmeDAO;
    @GetMapping
    public String listar(Model model){
        List<Filme> lista = filmeDAO.buscarTodos();
        model.addAttribute("filmes", lista);
        return "filme_listar";
    }
    @GetMapping("/filmesFavoritos")
    public String listarFavoritos(Model model){
        List<Filme> listarFavoritos = filmeDAO.buscarFavoritos();
        model.addAttribute("filmesFavoritos", listarFavoritos);
        return "filme_favoritos";
    }
    @GetMapping("adicionarFilmeFavorito/{id}")
    public String adicionarFilmeFavorito(@PathVariable int id, Model model){
        Filme filmeFavorito = filmeDAO.buscarPorId(id);
        model.addAttribute("filmesFavorito", filmeFavorito);
        filmeDAO.adicionarFilmeFavorito(id);
        return "redirect:/filme";
    }
    @GetMapping("removerFilmeFavorito/{id}")
    public String removerFilmeFavorito(@PathVariable int id){
        filmeDAO.removerFilmeFavorito(id);
        return "redirect:/filme";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "filme_editar";
    }
    @PostMapping("/editar")
    public String atualizar(Filme filme){
        filmeDAO.atualizar(filme);
        return "redirect:/filme";
    }
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        filmeDAO.remover(id);
        return "redirect:/filme";
    }
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("filme", new Filme());
        return "filme_novo";
    }

    @PostMapping("/novo")
    public String adicionar(Filme filme) {
        filmeDAO.adicionar(filme);
        return "redirect:/filme";
    }
    @GetMapping("salvar")
    public String salvarListaFilmes() throws StreamWriteException, DatabindException, IOException {
        filmeDAO.salvarJson();
        filmeDAO.salvarJsonFavoritos();
        return "redirect:/filme";
    }

    @GetMapping("carregar")
    public String carregarListaFilmes() throws StreamWriteException, DatabindException, IOException {
        filmeDAO.carregarJson();
        filmeDAO.carregarJsonFavoritos();
        return "redirect:/filme";
    }

    @GetMapping("like/{id}")
    public String like(@PathVariable int id) {
        filmeDAO.like(id);
        return "redirect:/filme";
    }
    @GetMapping("deslike/{id}")
    public String deslike(@PathVariable int id) {
        filmeDAO.deslike(id);
        return "redirect:/filme";
    }



}
