package view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ModelManager.VIAPetsModelManager;
import model.Customer;
import model.IllegalFirstNameException;
import model.IllegalPhoneNumberException;
import model.IllegalEmailException;


public class AddNewCustomerViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneNumberField;
  @FXML private TextField emailAddressField;
  @FXML private Button backButton;
  @FXML private Button addButton;
  @FXML private Button manageButton;

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
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

  }

  public void handleActions(ActionEvent e)
  {
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

    else if (e.getSource() == manageButton)
    {
      viewHandler.openView("ManageCustomerView");
    }

    else if (e.getSource() == addButton)
    {
      addNewCustomer();
    }

  }

  private void addNewCustomer()
  {

    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String phoneNumber = phoneNumberField.getText();
    String emailAddress = emailAddressField.getText();

    if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()
        || emailAddress.isEmpty())
    {

      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("All fields must be filled out!");
      alert.showAndWait();
      return;
    }


    if (firstName == null || firstName.length() < 3)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("First name must be at least 3 characters long.");
        alert.showAndWait();
        return;
      }

    if (!firstName.matches("[a-zA-z ]+"))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("First name can contain only letters!");
      alert.showAndWait();
      return;
    }

    if (!lastName.matches("[a-zA-z ]+"))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Last name can contain only letters!");
      alert.showAndWait();
      return;
    }



    if (!phoneNumber.matches("\\d+"))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Phone number can contain only digits!");
      alert.showAndWait();
      return;
    }


    if (!emailAddress.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Invalid email format. Expected format: user@host.domain.");
      alert.showAndWait();
      return;
    }


  try
  {
    Customer newCustomer = new Customer(firstName, lastName, phoneNumber, emailAddress);
    modelManager.addCostumer(newCustomer);
  }
  catch (IllegalEmailException e){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("The email address matches other email address in the system, enter other email address.");
    alert.showAndWait();
    return;
  }
  catch(IllegalPhoneNumberException e)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("The phone number matches other phone number in the system, enter other phone number.");
    alert.showAndWait();
    return;
  }

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("Success, Customer added successfully!");
    alert.showAndWait();
    resetFields();
  }

  private void resetFields() {
    firstNameField.clear();
    lastNameField.clear();
    phoneNumberField.clear();
    emailAddressField.clear();
  }
}

