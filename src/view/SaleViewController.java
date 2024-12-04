package view;

import ModelManager.VIAPetsModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Sale;
import javafx.scene.control.cell.PropertyValueFactory;


public class SaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button backButton;
  @FXML private Button addNewButton;
  @FXML private Button deleteButton;
  @FXML private TableView<Sale> salesTable;
  @FXML private TableColumn<Sale, String> petColumn;
  @FXML private TableColumn<Sale, String> customerColumn;
  @FXML private TableColumn<Sale, String> dateTimeColumn;
  @FXML private TableColumn<Sale, Double> priceColumn;

  private ObservableList<Sale> salesData = FXCollections.observableArrayList();

  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;

//    // Initialize salesData
//    salesData = FXCollections.observableArrayList();
//
//    // Bind columns to Sale properties
//    petColumn.setCellValueFactory(new PropertyValueFactory<>("pet"));
//    customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
//    dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
//    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//    // Load initial data
//    salesData.setAll(VIAPetsModelManager.getAllSales()); // Ensure modelManager.getSales() returns a list of Sale objects
//    salesTable.setItems(salesData);
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
//    // Refresh the data
//    salesData.setAll(VIAPetsModelManager.getAllSales());
//    salesTable.refresh();
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == addNewButton)
    {
      viewHandler.openView("AddSaleView");
    }
    else if (e.getSource() == deleteButton)
    {
      Sale selectedSale = salesTable.getSelectionModel().getSelectedItem();
      if (selectedSale != null)
      {
        modelManager.removeSale(selectedSale); // Remove from backend
        salesData.remove(selectedSale); // Remove from table
      }
    }
  }
}
