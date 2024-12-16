package view;
// Import necessary classes
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ModelManager.VIAPetsModelManager;
import model.Customer;
import Exeptions.IllegalPhoneNumberException;
import Exeptions.IllegalEmailException;


public class AddNewCustomerViewController
{
  // Scene and model objects for managing the view and data
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;
  // UI components from the FXML file
  @FXML private TextField firstNameField;// Text field for entering the first name
  @FXML private TextField lastNameField;// Text field for entering the last name
  @FXML private TextField phoneNumberField;// Text field for entering the phone number
  @FXML private TextField emailAddressField;// Text field for entering the email address
  @FXML private Button backButton;// Button for navigating back
  @FXML private Button addButton;// Button for adding a new customer
  @FXML private Button manageButton;// Button for navigating to manage customers

  // Initializes the controller with references to the ViewHandler, Scene, and ModelManager
  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    //Implements css source for the scene
    this.scene.getStylesheets().add(getClass().getResource("AddNewCustomer.css").toExternalForm());

  }

  // Returns the scene associated with this controller
  public Scene getScene()
  {
    return scene;
  }

  // Resets the view
  public void reset()
  {
    resetFields();
  }

  // Handles button actions based on the source of the event
  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == backButton)
    {
      // If the "Back" button is clicked, show a confirmation dialog
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to go back?", ButtonType.YES, ButtonType.NO);
      alert.setTitle("Back");
      alert.setHeaderText(null);

      alert.showAndWait();

      // Navigate back to the main view if confirmed
      if (alert.getResult() == ButtonType.YES)
      {
        viewHandler.openView("MainView");
      }
    }
    // If the "Manage" button is clicked, navigate to the ManageCustomerView
    else if (e.getSource() == manageButton)
    {
      viewHandler.openView("ManageCustomerView");
    }
    // If the "Add" button is clicked, attempt to add a new customer
    else if (e.getSource() == addButton)
    {
      addNewCustomer();
    }
  }

  // Adds a new customer after validating input fields
  private void addNewCustomer()
  {
    // Retrieve values from text fields
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String phoneNumber = phoneNumberField.getText();
    String emailAddress = emailAddressField.getText();

    // Check if any fields are empty
    if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()
        || emailAddress.isEmpty())
    {
      // Show an error alert if any field is empty
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("All fields must be filled out!");
      alert.showAndWait();
      return;
    }

    // Validate that the first name is at least 3 characters long
    if (firstName == null || firstName.length() < 3)
      {
        //Show an error alert if the firstName is empty or less than 3 characters
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
      //Show an error if the last name contains anything else than letters
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
      //Show an error if the email adress is not the format user@host.domain.
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