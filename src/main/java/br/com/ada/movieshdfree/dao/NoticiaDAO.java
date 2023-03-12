package br.com.ada.movieshdfree.dao;

import br.com.ada.movieshdfree.model.Noticia;
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
public class NoticiaDAO {
    private static List<Noticia> noticias = new ArrayList<>();
    private static int proximoId = 1;
    private static ObjectMapper objectMapper = new ObjectMapper();
    public void adicionar(Noticia noticia){
        noticia.setId(proximoId++);
        noticias.add(noticia);
    }
    public void atualizar(Noticia noticia){
        for (int i =0; i < noticias.size(); i++){
            Noticia n = noticias.get(i);
            if(n.getId() == noticia.getId()){
                noticias.set(i, noticia);
                break;
            }
        }
    }
    public void remover(int id){
        noticias.removeIf(f -> f.getId() == id);
    }
    public Noticia buscarPorId(int id){
        return noticias.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Noticia> buscarTodos(){
        return noticias;
    }
    public void salvarJsonNoticia() throws StreamWriteException, DatabindException, IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("src/main/resources/json/lista_filmes.json"), noticias);
    }
    public void carregarJsonNoticia() throws StreamReadException, DatabindException, IOException {
        List<Noticia> listaNoticias = objectMapper.readValue(new File("src/main/resources/json/lista_filmes.json"), new TypeReference<List<Noticia>>(){});
        noticias = listaNoticias;
        if(noticias.size()>0){
            proximoId = noticias.get(noticias.size()-1).getId()+1;
        }
    }
}
