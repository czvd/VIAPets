package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.StudentList;
import model.StudentModelManager;

public class AllStudentsViewController
{
  private Scene scene;
  private StudentModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button getButton;
  @FXML private Button backButton;
  @FXML private TextArea allStudentsArea;
  @FXML private MenuItem exitMenuItem;
  @FXML private CheckMenuItem editAreaMenuItem;
  @FXML private MenuItem aboutMenuItem;

  public void init(ViewHandler viewHandler, Scene scene, StudentModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
  }

  public void reset()
  {
    updateStudentArea();
  }

  public Scene getScene()
  {
    return scene;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == getButton)
    {
      updateStudentArea();
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
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
    else if (e.getSource() == editAreaMenuItem)
    {
      if (editAreaMenuItem.isSelected())
      {
        allStudentsArea.setEditable(true);
      }
      else
      {
        allStudentsArea.setEditable(false);
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

  private void updateStudentArea()
  {
    StudentList students = modelManager.getAllStudents();
    allStudentsArea.setText(students.toString());
  }
}

