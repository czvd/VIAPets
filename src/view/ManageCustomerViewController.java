package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ModelManager.VIAPetsModelManager;
import model.Customer;
import model.CustomerList;

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
  @FXML private TableView fleTableView;


  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
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
      viewHandler.openView("MainView");
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

      modelManager.changeFirstName(firstName, lastName, phoneNumber,
          emailAddress);
      modelManager.changeLastName(firstName, lastName, phoneNumber,
          emailAddress);
      modelManager.changePhoneNumber(firstName, lastName, phoneNumber,
          emailAddress);
      modelManager.changeEmailAddress(firstName, lastName, phoneNumber,
          emailAddress);
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
    fleTableView.getItems().clear();

    CustomerList customers = modelManager.getAllCustomers();
    for (int i = 0; i < customers.size(); i++)
    {
      fleTableView.getItems().add(customers.get(i));
    }
  }
  private void updateTableView(String name)
  {
    fleTableView.getItems().clear();

    CustomerList customers = modelManager.getCustomerByName(name);
    for (int i = 0; i < customers.size(); i++)
    {
      fleTableView.getItems().add(customers.get(i));
    }
  }

}


