package br.com.ada.movieshdfree.dao;

import br.com.ada.movieshdfree.model.Filme;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmeDAO {
    private static List<Filme> filmes = new ArrayList<>();
    private static List<Filme> filmesFavoritos = new ArrayList<>();
    private static int proximoId = 1;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public void adicionar(Filme filme){
        filme.setId(proximoId++);
        filmes.add(filme);
    }
    public void adicionarFilmeFavorito(int id){
        Filme filmeSelecionado = filmes.stream().filter(film -> film.getId()== id).findFirst().get();
        if (!filmesFavoritos.contains(filmeSelecionado)){
            filmesFavoritos.add(filmeSelecionado);
        }
    }
    public void removerFilmeFavorito(int id){
        Filme filmeSelecionado = filmesFavoritos.stream().filter(film -> film.getId()== id).findFirst().get();
        if (filmesFavoritos.contains(filmeSelecionado)){
            filmesFavoritos.remove(filmeSelecionado);
        }
    }

    public void atualizar(Filme filme){
        for (int i =0; i < filmes.size(); i++){
            Filme f = filmes.get(i);
            if(f.getId() == filme.getId()){
                filmes.set(i, filme);
                break;
            }
        }
    }
    public void remover(int id){
        filmes.removeIf(f -> f.getId() == id);
        filmesFavoritos.removeIf(f -> f.getId() == id);
    }
    public Filme buscarPorId(int id){
        return filmes.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Filme> buscarTodos(){
        return filmes;
    }
    public List<Filme> buscarFavoritos(){
        return filmesFavoritos;
    }
    public void like(int id){
        Filme filmeSelecionado = filmes.stream().filter(film -> film.getId() == id).findFirst().get();
        filmeSelecionado.setLikes(filmeSelecionado.getLikes()+1);

    }
    public void deslike(int id){
        Filme filmeSelecionado = filmes.stream().filter(film -> film.getId() == id).findFirst().get();
        filmeSelecionado.setLikes(filmeSelecionado.getLikes()-1);
    }

    public void salvarJson() throws StreamWriteException, DatabindException, IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("src/main/resources/json/lista_filmes.json"), filmes);
    }
    public void carregarJson() throws StreamReadException, DatabindException, IOException {
        List<Filme> listaFilmes = objectMapper.readValue(new File("src/main/resources/json/lista_filmes.json"), new TypeReference<List<Filme>>(){});
        filmes = listaFilmes;
        if(filmes.size()>0){
            proximoId = filmes.get(filmes.size()-1).getId()+1;
        }
    }

    public void salvarJsonFavoritos() throws StreamWriteException, DatabindException, IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("src/main/resources/json/lista_filmes.json"), filmesFavoritos);
    }
    public void carregarJsonFavoritos() throws StreamReadException, DatabindException, IOException {
        List<Filme> listaFilmes = objectMapper.readValue(new File("src/main/resources/json/lista_filmes.json"), new TypeReference<List<Filme>>(){});
        filmesFavoritos = listaFilmes;
        if(filmesFavoritos.size()>0){
            proximoId = filmesFavoritos.get(filmesFavoritos.size()-1).getId()+1;
        }
    }
}
