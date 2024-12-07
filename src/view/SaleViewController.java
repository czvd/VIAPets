package view;

import ModelManager.VIAPetsModelManager;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.NoSuchElementException;

public class SaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField typeField;
  @FXML private TextField nameField;
  @FXML private TextField ageField;
  @FXML private TextField colorField;
  @FXML private TextField priceField;
  @FXML private TextField genderField;
  @FXML private TextField commentField;
  @FXML private TextField speciesField;
  @FXML private TextField searchField;
  @FXML private Button backButton;
  @FXML private Button addNewButton;
  @FXML private Button deleteButton;

  //initializing sale table
  @FXML private TableView<Sale> SaleTableView = new TableView<>();
  @FXML private TableColumn<Sale, String> customerNameColumn;
  @FXML private TableColumn<Sale, String> customerPhoneColumn;
  @FXML private TableColumn<Sale, String> dateColumn;
  @FXML private TableColumn<Sale, String> priceColumn;

  private Sale selectedSale;

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;

    this.scene.getStylesheets()
        .add(getClass().getResource("SaleView.css").toExternalForm());

    //sale table
    customerNameColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getCustomer().getFirstName())
    );

    customerPhoneColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getCustomer().getPhoneNumber())
    );

    dateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getDateOfSale().toString())
    );

    priceColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(String.format("%.2f", cellData.getValue().getFinalPrice()))
    );

    customerNameColumn.setSortable(false);
    customerPhoneColumn.setSortable(false);
    dateColumn.setSortable(false);
    priceColumn.setSortable(false);

    TableView.TableViewSelectionModel<Sale> selectionModel = SaleTableView.getSelectionModel();
    ObservableList<Sale> selectedItems = selectionModel.getSelectedItems();

    selectedItems.addListener(
        new ListChangeListener<Sale>() {
          @Override
          public void onChanged(
              Change<? extends Sale> change) {
            try
            {
              Sale temp = change.getList().getFirst();
              if (temp != null)
              {
                //selectedSale = new Sale(temp.getPet(), temp.getCustomer(), temp.getDateOfSale(), temp.getFinalPrice());
                selectedSale = temp;
                typeField.setText(temp.getPet().getTypeString());
                nameField.setText(temp.getPet().getName());
                ageField.setText(String.valueOf(temp.getPet().getAge()));
                colorField.setText(temp.getPet().getColor());
                priceField.setText(String.valueOf(temp.getPet().getPrice()));
                genderField.setText(temp.getPet().getGender());
                commentField.setText(temp.getPet().getComment());
                speciesField.setText(temp.getPet().getSpecies());
              }
            }
            catch (NullPointerException e)
            {
              System.out.println("no elements found");
            }
            catch (NoSuchElementException e)
            {
              System.out.println("customerTableView, customer changed");
            }
          }
        });
    updateSaleTable();
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updateSaleTable();
    modelManager.save();
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
    else if (e.getSource() == addNewButton)
    {
      viewHandler.openView("AddSaleView");
    }
    else if (e.getSource() == searchField)
    {
      String query = searchField.getText().trim();
      if (query.isEmpty())
      {
        updateSaleTable();
      }
      else
      {
        updateSaleTable(query);
      }
    }
    else if (e.getSource() == deleteButton)
    {
      if (selectedSale != null)
      {
        System.out.println("Attempting to delete sale: " + selectedSale);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Do you really want to remove the sale?", ButtonType.YES,
            ButtonType.NO);
        alert.setTitle("Delete Sale");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
          // Remove the selected sale from the model
          modelManager.removeSale(selectedSale);

          // Debug: Print the sales list after deletion
          System.out.println("Sales list after deletion:");
          SaleList sales = modelManager.getAllSales();
          for (int i = 0; i < sales.size(); i++) {
            Sale sale = sales.get(i);
            System.out.println(sale);
          }

          // Refresh the table
          updateSaleTable();

          // Clear input fields
          typeField.setText("");
          nameField.setText("");
          ageField.setText("");
          colorField.setText("");
          priceField.setText("");
          genderField.setText("");
          commentField.setText("");
          speciesField.setText("");

          // Clear selected sale reference
          selectedSale = null;

          // Show success message
          Alert successAlert = new Alert(Alert.AlertType.INFORMATION,
              "The sale has been successfully removed.", ButtonType.OK);
          successAlert.setTitle("Sale Removed");
          successAlert.setHeaderText(null);
          successAlert.showAndWait();
        }
      }
      else
      {
        // Show warning if no sale is selected
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Please select a sale to delete.", ButtonType.OK);
        alert.setTitle("No Sale Selected");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
    }
  }

  private void updateSaleTable() {

    if (SaleTableView != null)
    {
      // Clear the current table data
      SaleTableView.getItems().clear();
    }
    // Fetch updated sales from the model and repopulate the table
    SaleList sales = modelManager.getAllSales(); // Ensure this gives the latest list
    for (int i = 0; i < sales.size(); i++)
    {
      SaleTableView.getItems().add(sales.get(i));
    }
  }

  private void updateSaleTable(String phoneNr)
  {
    if (SaleTableView != null)
    {
      SaleTableView.getItems().clear();
    }

    SaleList sales = modelManager.getAllSales();
    for (int i =0; i< sales.size();i++ )
    {
      Sale sale = sales.get(i);

      if (sale.getCustomer().getPhoneNumber().equals(phoneNr)) {
        SaleTableView.getItems().add(sale); // Add the matching sale to the table
      }
    }
  }
}


