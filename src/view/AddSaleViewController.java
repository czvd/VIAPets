package view;

import ModelManager.VIAPetsModelManager;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.NoSuchElementException;

public class AddSaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button backButton;
  @FXML private Button saleViewButton;
  @FXML private Button deleteButton;
  @FXML private ComboBox<Customer> customerList;
  //initializing costumer table
  @FXML private TableView<Customer> customerTableView = new TableView<>();
  @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Customer, String> emailAddressColumn;
  @FXML private TableColumn<Customer, String> phoneNumberColumn;
  //initializing pet table
  @FXML private TableView<Pet> petTableView = new TableView<>();
  @FXML private TableColumn<Pet, String> petNameColumn;
  @FXML private TableColumn<Pet, String> petAgeColumn;
  @FXML private TableColumn<Pet, String> petColorColumn;
  @FXML private TableColumn<Pet, String> petGenderColumn;
  @FXML private TableColumn<Pet, String> petCommentColumn;
  //type selector(ComboBox)
  @FXML private ComboBox<String> typeSelect = new ComboBox<>();

  @FXML private TextField DateNTimeField;
  @FXML private TextField priceField;
  private Customer selectedCustomer;
  private Pet selectedPet;
  private String price;

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    DateNTimeField.setText(Date.today().toString());
    //type select comboBox
    typeSelect.getItems().add("SelectType");
    typeSelect.getItems().add("Dog");
    typeSelect.getItems().add("Cat");
    typeSelect.getItems().add("Bird");
    typeSelect.getItems().add("Fish");
    typeSelect.getItems().add("Rodent");
    typeSelect.getItems().add("Various");
    typeSelect.getSelectionModel().selectFirst();
    //pet table
    petNameColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("name"));
    petAgeColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("age"));
    petColorColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("color"));
    petGenderColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("gender"));
    petCommentColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("comment"));

    petNameColumn.setSortable(false);
    petAgeColumn.setSortable(false);
    petColorColumn.setSortable(false);
    petGenderColumn.setSortable(false);
    petCommentColumn.setSortable(false);
    updatePetTable();
    TableView.TableViewSelectionModel<Pet> selectionModel =
        petTableView.getSelectionModel();
    ObservableList<Pet> selectedItems =
        selectionModel.getSelectedItems();

    selectedItems.addListener(
        new ListChangeListener<Pet>() {
          @Override
          public void onChanged(
              Change<? extends Pet> change) {
            try
            {
              Pet temp = change.getList().getFirst();
              if (temp != null)
              {
                price = Double.toString(temp.getPrice());
                setPriceField();

                //selectedPet = new Pet();
                switch (temp)
                {
                  case Cat cat -> selectedPet = new Cat(cat);
                  case Fish fish -> selectedPet = new Fish(fish);
                  case Dog dog -> selectedPet = new Dog(dog);
                  case Rodent rodent -> selectedPet = new Rodent(rodent);
                  case Bird bird -> selectedPet = new Bird(bird);
                  case Various various -> selectedPet = new Various(various);
                  default -> System.err.println("Pet tpe is not correct");
                }

              }
            }
            catch (NullPointerException e)
            {
              System.out.println("no elements found");
            }
            catch (NoSuchElementException e)
            {
              System.out.println("Table view, customer changed");
            }
          }
        });
    //customer table
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
    emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("emailAddress"));
    phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));

    firstNameColumn.setSortable(false);
    lastNameColumn.setSortable(false);
    emailAddressColumn.setSortable(false);
    phoneNumberColumn.setSortable(false);
    updateCostumerTable();
   TableView.TableViewSelectionModel<Customer> SelectionModel =
        customerTableView.getSelectionModel();
    ObservableList<Customer> selectedItems1 =
        SelectionModel.getSelectedItems();

    selectedItems1.addListener(
        new ListChangeListener<Customer>() {
          @Override
          public void onChanged(
              Change<? extends Customer> change) {
            try
            {
              Customer temp = change.getList().getFirst();
              if (temp != null)
              {
                selectedCustomer = new Customer(temp.getFirstName(),temp.getLastName(), temp.getPhoneNumber(), temp.getEmailAddress());
              }
            }
            catch (NullPointerException e)
            {
              System.out.println("no elements found");
            }
            catch (NoSuchElementException e)
            {
              System.out.println("Table view, customer changed");
            }
          }
        });
  }

  private void setPriceField()
  {

    priceField.setText(price.substring(0,price.length()-2));
  }

  private void updateCostumerTable()
  {

    if (customerTableView != null)
    {
      customerTableView.getItems().clear();
    }


    CustomerList customers = modelManager.getAllCustomers();
    for (int i = 0; i < customers.size(); i++)
    {
      customerTableView.getItems().add(customers.get(i));
    }
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
      petTableView.getItems().add(pets.get(i));
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

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updateCostumerTable();
    updatePetTable();
    modelManager.save();
  }

  public void handleActions(ActionEvent e)
  {
    price = priceField.getText();
    if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == saleViewButton)
    {
      viewHandler.openView("SaleView");
    }
    else if (e.getSource()== priceField)
    {
      if (!price.matches("\\d+"))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("The price should be postive and a number!");
        alert.showAndWait();
        return;
      }
    }
    else if (e.getSource()==typeSelect)
    {
      int selected = typeSelect.getSelectionModel().getSelectedIndex();
      switch (selected)
      {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
          updatePetTable(selected);
          break;
        case 0:
        default:
          updatePetTable();
      }
    }
  }
}
