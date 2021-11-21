package gui.tela_login;

import database.DataBase;
import gui.tela_principal.ControllerTelaPrincipal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class ControllerTelaLogin implements Initializable {

  public Button btn_entrar;
  public TextField fd_user;
  public PasswordField fd_password;
  public Label lbl_status;

  @FXML
  public void onClick(ActionEvent event){
    if(event.getSource().equals(btn_entrar)){
      Platform.runLater(() -> {
        try {
          Parent root = FXMLLoader.load(getClass().getResource("/gui/tela_principal/ViewTelaPrincipal.fxml"));
          Stage stage = new Stage();
          stage.setTitle("Acervo");
          stage.setResizable(false);
          stage.setScene(new Scene(root));
          DataBase dataBase = new DataBase(fd_user.getText(),fd_password.getText());
          try {
            dataBase.getConnection();
            ControllerTelaPrincipal.dataBase = dataBase;
            stage.setUserData(dataBase);
            stage.show();
            Stage thisStage = (Stage) btn_entrar.getScene().getWindow();
            thisStage.close();
          }catch (SQLException e0){
            lbl_status.setVisible(true);
            lbl_status.setText("Erro ao logar");
            new Thread(() -> {
              try {
                sleep(4000);
                lbl_status.setVisible(false);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }).start();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lbl_status.setVisible(false);
    fd_user.setText("root");
  }
}
