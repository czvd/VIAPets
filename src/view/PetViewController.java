package view;

import ModelManager.VIAPetsModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.NoSuchElementException;

public class PetViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  private Pet selectedPet;
  @FXML private Button addPetButton;
  @FXML private Button backButton;
  @FXML private TableView<Pet> petTableView;
  @FXML private TableColumn<Pet, String> breedspecColumn;
  @FXML private TableColumn<Pet, String> colorColumn;
  @FXML private TableColumn<Pet, String> genderColumn;
  @FXML private TableColumn<Pet, String> kennelColumn;

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
  }

  private void updatePetTable()
  {
    if (petTableView != null)
    {
      petTableView.getItems().clear();
    }

    PetList pets = modelManager.getAllPets();
    for (int i = 0; i < pets.size(); i++)
    {
      if (!pets.get(i).getInKennel())
      {
        petTableView.getItems().add(pets.get(i));
      }
    }
  }

  private void updatePetTable(int type)
  {

    if (petTableView != null)
    {
      petTableView.getItems().clear();
    }


    PetList pets = modelManager.getAllPets().getPetsByType(type);
    for (int i = 0; i < pets.size(); i++)
    {
      petTableView.getItems().add(pets.get(i));
    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == addPetButton)
    {
      viewHandler.openView("AddPetView");
    }

    if (e.getSource() == backButton)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to go back?", ButtonType.YES, ButtonType.NO);
      alert.setTitle("Back");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        viewHandler.openView("MainView");
      }
    }
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updatePetTable();
    modelManager.save();
  }
}
