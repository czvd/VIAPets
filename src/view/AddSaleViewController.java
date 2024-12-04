package view;

import ModelManager.VIAPetsModelManager;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.CustomerList;
import model.Date;


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
  @FXML private TableView<Customer> customerTableView = new TableView<>();
  @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Customer, String> emailAddressColumn;
  @FXML private TableColumn<Customer, String> phoneNumberColumn;
  @FXML private TextField DateNTimeField;
  @FXML private TextField priceField;
  private Customer selectedCustomer;
  private String price;

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    DateNTimeField.setText(Date.today().toString());

    //pet table

    //customer table
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
    emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("emailAddress"));
    phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));

    firstNameColumn.setSortable(false);
    lastNameColumn.setSortable(false);
    emailAddressColumn.setSortable(false);
    phoneNumberColumn.setSortable(false);
   TableView.TableViewSelectionModel<Customer> selectionModel =
        customerTableView.getSelectionModel();
    ObservableList<Customer> selectedItems =
        selectionModel.getSelectedItems();

    selectedItems.addListener(
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
    updateCostumerTable();
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
      System.out.println(customers.get(i));
    }
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {

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
  }
}
