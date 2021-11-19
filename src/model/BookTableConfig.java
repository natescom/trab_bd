package model;

import javafx.beans.property.SimpleStringProperty;

public class BookTableConfig {
  private SimpleStringProperty titulo;
  private SimpleStringProperty editora;
  private SimpleStringProperty autor;
  private SimpleStringProperty volume;
  private SimpleStringProperty edicao;
  private SimpleStringProperty ano;

  public BookTableConfig() {
    this.titulo = new SimpleStringProperty("");
    this.editora = new SimpleStringProperty("");
    this.autor = new SimpleStringProperty("");
    this.volume = new SimpleStringProperty("");
    this.edicao = new SimpleStringProperty("");
    this.ano = new SimpleStringProperty("");
  }

  public String getTitulo() {
    return titulo.get();
  }

  public SimpleStringProperty tituloProperty() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo.set(titulo);
  }

  public String getEditora() {
    return editora.get();
  }

  public SimpleStringProperty editoraProperty() {
    return editora;
  }

  public void setEditora(String editora) {
    this.editora.set(editora);
  }

  public String getAutor() {
    return autor.get();
  }

  public SimpleStringProperty autorProperty() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor.set(autor);
  }

  public String getVolume() {
    return volume.get();
  }

  public SimpleStringProperty volumeProperty() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume.set(volume);
  }

  public String getEdicao() {
    return edicao.get();
  }

  public SimpleStringProperty edicaoProperty() {
    return edicao;
  }

  public void setEdicao(String edicao) {
    this.edicao.set(edicao);
  }

  public String getAno() {
    return ano.get();
  }

  public SimpleStringProperty anoProperty() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano.set(ano);
  }
}
