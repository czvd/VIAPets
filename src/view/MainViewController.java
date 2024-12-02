package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
//import model.StudentModelManager;

public class MainViewController
{
  private Scene scene;
  //private StudentModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML Button allStudentsButton;
  @FXML Button changeCountryButton;
  @FXML MenuItem exitMenuItem;
  @FXML MenuItem aboutMenuItem;

  public void init(ViewHandler viewHandler, Scene scene )//StudentModelManager modelManager
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    //this.modelManager = modelManager;
  }

  public void reset()
  {

  }

  public Scene getScene()
  {
    return scene;
  }

  public void handleActions(ActionEvent e)
  {
    if(e.getSource() == allStudentsButton)
    {
      viewHandler.openView("AllStudentsView");
    }
    else if(e.getSource() == changeCountryButton)
    {
      viewHandler.openView("ChangeCountryView");
    }
    else if (e.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to exit the program?",
          ButtonType.YES, ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
    else if (e.getSource() == aboutMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText(null);
      alert.setTitle("About");
      alert.setContentText("This is just a little program that demonstrates some of the GUI features in Java");
      alert.showAndWait();
    }
  }
}