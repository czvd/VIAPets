package view;

import ModelManager.VIAPetsModelManager;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import model.*;

import java.util.NoSuchElementException;

public class SaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField searchField;
  @FXML private Button backButton;
  @FXML private Button addNewButton;
  @FXML private Button deleteButton;

  //initializing sale table
  @FXML private TableView<Sale> SaleTableView = new TableView<>();
  @FXML private TableColumn<Sale, String> customerColumn;
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
        new SimpleStringProperty(cellData.getValue().getCustomer().getName()));

    customerPhoneColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getCustomer().getPhoneNumber()));

    dateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getDate().toString()));

    priceColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(String.format("%.2f", cellData.getValue().getFinalPrice())));

    customerColumn.setSortable(false);
    customerNameColumn.setSortable(false);
    customerPhoneColumn.setSortable(false);
    dateColumn.setSortable(false);
    priceColumn.setSortable(false);

    TableView.TableViewSelectionModel<Sale> selectionModel = SaleTableView.getSelectionModel();
    ObservableList<Sale> selectedItems = selectionModel.getSelectedItems();

    selectedItems.addListener(new ListChangeListener<Sale>()
    {
      @Override public void onChanged(Change<? extends Sale> change)
      {
        try
        {
          Sale temp = change.getList().getFirst();
          if (temp != null)
          {
            selectedSale = new Sale(temp.getPet(), temp.getCustomer(),
                (Date) temp.getDate(), temp.getFinalPrice());
          }
        }
        catch (NullPointerException e)
        {
          System.out.println("no elements found");
        }
        catch (NoSuchElementException e)
        {
          System.out.println("Table View, sale changed");
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
  }

  private void updateSaleTable()
  {

    if (SaleTableView != null)
    {
      SaleTableView.getItems().clear();
    }

    SaleList sales = modelManager.getAllSales();
    for (int i = 0; i < sales.size(); i++)
    {
      SaleTableView.getItems().add(sales.get(i));
    }
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
    else if (e.getSource() == deleteButton)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to remove?", ButtonType.YES, ButtonType.NO);
      alert.setTitle("Back");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        modelManager.removeSale(selectedSale);
      }
    }
  }
}


