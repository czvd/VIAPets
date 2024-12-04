package view;

import ModelManager.VIAPetsModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import model.Customer;
import model.CustomerList;
import model.Sale;

public class SaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button backButton;
  @FXML private Button addNewButton;
  @FXML private Button deleteButton;

  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;

    this.scene.getStylesheets().add(getClass().getResource("SaleView.css").toExternalForm());
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
    else if (e.getSource() == addNewButton)
    {
      viewHandler.openView("AddSaleView");
    }
    else if(e.getSource() == deleteButton)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to go back?", ButtonType.YES, ButtonType.NO);
      alert.setTitle("Back");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        //Sale sale = new Sale(); // there are 2 sale constructors, which one should I use?
        //modelManager.removeSale(sale);
        //updateTableView();
      }
    }
  }

}
