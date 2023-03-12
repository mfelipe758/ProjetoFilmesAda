package br.com.ada.movieshdfree.controller;

import br.com.ada.movieshdfree.dao.FilmeDAO;
import br.com.ada.movieshdfree.dao.NoticiaDAO;
import br.com.ada.movieshdfree.model.Filme;
import br.com.ada.movieshdfree.model.Noticia;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/noticia")
public class NoticiaController {
    @Autowired
    private NoticiaDAO noticiaDAO;
    @GetMapping
    public String listar(Model model){
        List<Noticia> lista = noticiaDAO.buscarTodos();
        model.addAttribute("noticias", lista);
        return "noticia_listar";
    }
    @GetMapping("/editarNoticia/{id}")
    public String editar(@PathVariable int id, Model model){
        Noticia noticia = noticiaDAO.buscarPorId(id);
        model.addAttribute("noticia", noticia);
        return "noticia_editar";
    }
    @PostMapping("/editarNoticia")
    public String atualizar(Noticia noticia){
        noticiaDAO.atualizar(noticia);
        return "redirect:/noticia";
    }
    @GetMapping("/removerNoticia/{id}")
    public String remover(@PathVariable int id) {
        noticiaDAO.remover(id);
        return "redirect:/noticia";
    }
    @GetMapping("/novaNoticia")
    public String novo(Model model) {
        model.addAttribute("noticia", new Noticia());
        return "noticia_novo";
    }

    @PostMapping("/novaNoticia")
    public String adicionar(Noticia noticia) {
        noticiaDAO.adicionar(noticia);
        return "redirect:/noticia";
    }
    @GetMapping("salvarNoticia")
    public String salvarListaFilmes() throws StreamWriteException, DatabindException, IOException {
        noticiaDAO.salvarJsonNoticia();
        return "redirect:/noticia";
    }

    @GetMapping("carregarNoticia")
    public String carregarListaFilmes() throws StreamWriteException, DatabindException, IOException {
        noticiaDAO.carregarJsonNoticia();
        return "redirect:/noticia";
    }
}
