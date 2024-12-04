package view;

import ModelManager.VIAPetsModelManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.Pet;
import model.Dog;
import model.Cat;
import model.Bird;
import model.Rodent;
import model.Fish;
import model.Various;

public class AddPetViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private ComboBox comboBox;
  @FXML private TextField colorField;
  @FXML private TextField ageField;
  @FXML private TextField nameField;
  @FXML private TextArea commentArea;
  @FXML private RadioButton maleRadio;
  @FXML private RadioButton femaleRadio;
  @FXML private CheckBox kennelCheck;

  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
  }

  @FXML
  public void initialize() {
    comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset() {}
}
