package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import model.Student;
//import model.StudentList;
//import model.StudentModelManager;

public class ChangeCountryViewController
{
  private Scene scene;
  //private StudentModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField countryField;
  @FXML private ComboBox<Student> studentBox;
  @FXML private Button updateButton;
  @FXML private Button backButton;
  @FXML private MenuItem exitMenuItem;
  @FXML private CheckMenuItem editFieldsMenuItem;
  @FXML private MenuItem aboutMenuItem;

  public void init(ViewHandler viewHandler, Scene scene)//StudentModelManager modelManager
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    //this.modelManager = modelManager;
  }

  public void reset()
  {
    updateStudentBox();

    //Student temp = studentBox.getSelectionModel().getSelectedItem();

    /*if (temp != null)
    {
      firstNameField.setText(temp.getFirstName());
      lastNameField.setText(temp.getLastName());
      countryField.setPromptText(temp.getCountry());
    }*/
  }

  public Scene getScene()
  {
    return scene;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == updateButton)
    {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String country = countryField.getText();

      if (country.equals(""))
      {
        country = "?";
      }

      //modelManager.changeCountry(firstName, lastName, country);
      updateStudentBox();
      countryField.setText("");
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == studentBox)
    {
      /*Student temp = studentBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        countryField.setPromptText(temp.getCountry());
      }*/
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
    else if (e.getSource() == editFieldsMenuItem)
    {
      if (editFieldsMenuItem.isSelected())
      {
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
      }
      else
      {
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
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

  private void updateStudentBox()
  {
    int currentIndex = studentBox.getSelectionModel().getSelectedIndex();

    studentBox.getItems().clear();

    /*StudentList students = modelManager.getAllStudents();
    for (int i = 0; i < students.size(); i++)
    {
      studentBox.getItems().add(students.get(i));
    }

    if (currentIndex == -1 && studentBox.getItems().size() > 0)
    {
      studentBox.getSelectionModel().select(0);
    }
    else
    {
      studentBox.getSelectionModel().select(currentIndex);
    }*/
  }
}
