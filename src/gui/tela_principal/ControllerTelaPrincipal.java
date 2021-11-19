package gui.tela_principal;

import database.DataBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Book;
import model.BookTableConfig;
import model.DbConvert;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerTelaPrincipal implements Initializable {
  public Button btn_add;
  public Button btn_edit;
  public Button btn_delete;
  public TableColumn col_tit;
  public TableColumn col_edi;
  public TableColumn col_aut;
  public TableColumn col_vol;
  public TableColumn col_edc;
  public TableColumn col_pub;
  public TableView tab_acervo;

  private ObservableList<Book> acervo;
  private DbConvert dbConvert;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    updateTable();
  }


  public void updateTable(){
    acervo = FXCollections.observableArrayList();
    Platform.runLater(() -> {
      col_tit.setCellValueFactory(new PropertyValueFactory<BookTableConfig, String>("titulo"));
      col_edi.setCellValueFactory(new PropertyValueFactory<BookTableConfig, String>("editora"));
      col_aut.setCellValueFactory(new PropertyValueFactory<BookTableConfig, String>("autor"));
      col_edc.setCellValueFactory(new PropertyValueFactory<BookTableConfig, String>("edicao"));
      col_vol.setCellValueFactory(new PropertyValueFactory<BookTableConfig, String>("volume"));
      col_pub.setCellValueFactory(new PropertyValueFactory<BookTableConfig, String>("ano_publicacao"));
      DataBase dataBase = new DataBase("root","");
      dbConvert = new DbConvert(dataBase);
      acervo.addAll(dbConvert.getAcervo());
      tab_acervo.setItems(acervo);
    });
  }


  public void onClick(ActionEvent event) {
    if (event.getSource().equals(btn_add)){
      openViewEdit("Novo livro",null);
      Stage stage = (Stage) btn_add.getScene().getWindow();
      stage.close();
    }
    if (event.getSource().equals(btn_edit)){
      if(!tab_acervo.getSelectionModel().isEmpty()) {
        openViewEdit("Editar", acervo.get(tab_acervo.getSelectionModel().getSelectedIndex()));
        Stage stage = (Stage) btn_edit.getScene().getWindow();
        stage.close();
      }
    }
    if (event.getSource().equals(btn_delete)){
      if(!tab_acervo.getSelectionModel().isEmpty()) {
        dbConvert.deleteBook(acervo.get(tab_acervo.getSelectionModel().getSelectedIndex()));
        updateTable();
      }
    }
  }

  public void openViewEdit(String title, Book book){
    Platform.runLater(() -> {
      try {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/tela_addedit/ViewTelaAddedit.fxml"));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        ArrayList tupla = new ArrayList();
        tupla.add(dbConvert);
        tupla.add(book);
        stage.setUserData(tupla);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }


}
