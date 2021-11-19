package gui.tela_addedit;

import database.DataBase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import model.DbConvert;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerTelaAddedit implements Initializable {
  public Button btn_confirmar;
  public Button btn_cancelar;
  public TextField fd_titulo;
  public TextField fd_editora;
  public TextField fd_autor;
  public TextField fd_volume;
  public TextField fd_edicao;
  public DatePicker fd_publi;

  private Book book;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    fd_volume.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        fd_volume.setText(newValue.replaceAll("[^\\d]", ""));
      }
    });
    fd_edicao.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        fd_edicao.setText(newValue.replaceAll("[^\\d]", ""));
      }
    });
    Platform.runLater(() -> {
      Stage stage = (Stage)btn_confirmar.getScene().getWindow();
      book = (Book) ((ArrayList) stage.getUserData()).get(1);
      if(book != null) {
        fd_titulo.setText(book.getTitulo());
        fd_autor.setText(book.getAutor());
        fd_editora.setText(book.getEditora());
        fd_edicao.setText("" + book.getEdicao());
        fd_volume.setText("" + book.getVolume());
        fd_publi.setValue(LocalDate.parse(book.getAno_publicacao()));
      }
    });
  }

  @FXML
  public void onClick(ActionEvent event){
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    DbConvert dbConvert = (DbConvert) ((ArrayList) stage.getUserData()).get(0);
    if (event.getSource().equals(btn_confirmar)){
      if(book==null){
        book = new Book(fd_titulo.getText(),
            fd_editora.getText(),
            fd_autor.getText(),
            Integer.parseInt(fd_volume.getText()),
            Integer.parseInt(fd_edicao.getText()),
            fd_publi.getValue().toString());
        dbConvert.addBook(book);
      }else{
        book.setTitulo(fd_titulo.getText());
        book.setEditora(fd_editora.getText());
        book.setAutor(fd_autor.getText());
        book.setVolume(Integer.parseInt(fd_volume.getText()));
        book.setEdicao(Integer.parseInt(fd_edicao.getText()));
        book.setAno_publicacao(fd_publi.getValue().toString());
        dbConvert.updateBook(book);
      }
      stage.close();
    }
    if (event.getSource().equals(btn_cancelar)){
      stage.close();
    }
      openViewTelaPrincial(dbConvert.getDataBase());
  }

  public void openViewTelaPrincial(DataBase dataBase){
    Platform.runLater(() -> {
      try {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/tela_principal/ViewTelaPrincipal.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Acervo");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.setUserData(dataBase);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }


}
