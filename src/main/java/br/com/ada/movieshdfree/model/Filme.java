package br.com.ada.movieshdfree.model;

public class Filme {
    private int id;
    private String titulo;
    private String genero;
    private String duracao;
    private String sinopse;
    private String imagem;
    private String imdb;
    private int likes;

    public Filme(int id, String titulo, String genero, String duracao, String sinopse, String imagem, String imdb, int likes) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.imagem = imagem;
        this.imdb = imdb;
        this.likes = likes;
    }

    public Filme(String titulo, String genero, String duracao, String sinopse, String imagem, String imdb) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.imagem = imagem;
        this.imdb = imdb;
    }

    public Filme(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
