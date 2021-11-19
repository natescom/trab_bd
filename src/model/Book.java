package model;

public class Book {
  private int id;
  private String titulo;
  private String editora;
  private String autor;
  private int volume;
  private int edicao;
  private String ano_publicacao;


  public Book(int id,String titulo, String editora, String autor) {
    this.id = id;
    this.titulo = titulo;
    this.editora = editora;
    this.autor = autor;
  }

  public Book(int id, String titulo, String editora, String autor, int volume, int edicao, String ano_publicacao) {
    this.id = id;
    this.titulo = titulo;
    this.editora = editora;
    this.autor = autor;
    this.volume = volume;
    this.edicao = edicao;
    this.ano_publicacao = ano_publicacao;
  }

  public Book(String titulo, String editora, String autor, int volume, int edicao, String ano_publicacao) {
    this.titulo = titulo;
    this.editora = editora;
    this.autor = autor;
    this.volume = volume;
    this.edicao = edicao;
    this.ano_publicacao = ano_publicacao;
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getEditora() {
    return editora;
  }

  public void setEditora(String editora) {
    this.editora = editora;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public int getEdicao() {
    return edicao;
  }

  public void setEdicao(int edicao) {
    this.edicao = edicao;
  }

  public String getAno_publicacao() {
    return ano_publicacao;
  }

  public void setAno_publicacao(String ano_publicacao) {
    this.ano_publicacao = ano_publicacao;
  }
}
