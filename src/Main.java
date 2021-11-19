import gui.tela_addedit.ControllerTelaAddedit;
import gui.tela_login.ControllerTelaLogin;
import gui.tela_principal.ControllerTelaPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/tela_login/ViewTelaLogin.fxml"));
    new ControllerTelaLogin();
    new ControllerTelaAddedit();
    new ControllerTelaPrincipal();
    //loader.setController();
    Parent root = loader.load();
    Scene scene = new Scene(root, 600, 400);
    scene.getStylesheets().add("gui/main.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
