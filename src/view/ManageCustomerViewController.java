package view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ModelManager.VIAPetsModelManager;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.CustomerList;

import java.util.NoSuchElementException;

public class ManageCustomerViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;


  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneNumberField;
  @FXML private TextField emailAddressField;
  @FXML private TextField searchField;
  @FXML private Button backButton;
  @FXML private Button editButton;
  @FXML private Button deleteButton;
  @FXML private Button manageButton;
  @FXML private Button addNewButton;
  @FXML private TableView<Customer> fleTableView = new TableView<>();
 @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Customer, String> emailAddressColumn;
  private Customer selectedCustomer;


  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
    emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("emailAddress"));

    firstNameColumn.setSortable(false);
    lastNameColumn.setSortable(false);
    emailAddressColumn.setSortable(false);

    TableView.TableViewSelectionModel<Customer> selectionModel =
        fleTableView.getSelectionModel();
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
                firstNameField.setText(temp.getFirstName());
                lastNameField.setText(temp.getLastName());
                emailAddressField.setText(temp.getEmailAddress());
                phoneNumberField.setText(temp.getPhoneNumber());
              }
            }
            catch (NullPointerException e)
            {
              System.out.println("happened..");
            }
            catch (NoSuchElementException e)
            {
              System.out.println("Table view, customer changed");
            }
          }
        });

    updateTableView();
  }
  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updateTableView();
  }

  public void handleActions(ActionEvent e)
  {
    if(e.getSource()== backButton)
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

    else if(e.getSource()== addNewButton)
    {
      viewHandler.openView("AddNewCustomerView");
    }
    else if(e.getSource()== editButton)
    {

      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String phoneNumber = phoneNumberField.getText();
      String emailAddress = emailAddressField.getText();
      Customer customer = new Customer(firstName,lastName,phoneNumber,emailAddress);
      modelManager.removeCustomer(selectedCustomer);
      modelManager.addCostumer(customer);
      updateTableView();
      firstNameField.setText("");
      lastNameField.setText("");
      phoneNumberField.setText("");
      emailAddressField.setText("");
    }

    else if(e.getSource() == deleteButton)
    {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String phoneNumber = phoneNumberField.getText();
      String emailAddress = emailAddressField.getText();

      Customer customer = new Customer(firstName,lastName,phoneNumber,emailAddress);
      modelManager.removeCustomer(customer);
      updateTableView();
      firstNameField.setText("");
      lastNameField.setText("");
      phoneNumberField.setText("");
      emailAddressField.setText("");
    }
    else if (e.getSource() == fleTableView)
    {
      Customer temp = (Customer) fleTableView.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        emailAddressField.setText(temp.getEmailAddress());
      }
    }
    else if (e.getSource()== searchField)
    {
      updateTableView(searchField.getText());
    }
  }



  private void updateTableView()
  {
    if (fleTableView != null)
    {
      fleTableView.getItems().clear();
    }



    CustomerList customers = modelManager.getAllCustomers();
    for (int i = 0; i < customers.size(); i++)
    {
      fleTableView.getItems().add(customers.get(i));
    }
  }
  private void updateTableView(String name)
  {
    fleTableView.getItems().clear(); // Clear the table

    CustomerList customers = modelManager.getAllCustomers(); // Get all customers
    for (int i =0; i< customers.size();i++ )
    {
      Customer customer = customers.get(i);
      // Check if the customer's first or last name contains the search text
      if (customer.getFirstName().equals(name)
          || customer.getLastName().equals(name))
      {
        fleTableView.getItems()
            .add(customer); // Add matching customers to the table
      }
    }
  }

}
/*if (fleTableView != null)
    {
      fleTableView.getItems().clear();
    }
    CustomerList customers = modelManager.getCustomerByName(name);
    for (int i = 0; i < customers.size(); i++)
    {
      fleTableView.getItems().add(customers.get(i));
    }*/


