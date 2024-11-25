import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VIAPetsGUI extends Application
{
  public void start(Stage window) throws IOException
  {
    window.setTitle("VIAPets GUI Window");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("VIAPetsGUI.fxml"));
    Scene scene = new Scene(loader.load());

    window.setScene(scene);
    window.show();
  }
}
