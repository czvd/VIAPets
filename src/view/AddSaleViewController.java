package view;

import ModelManager.VIAPetsModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class AddSaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button backButton;
  @FXML private Button saleViewButton;
  @FXML private Button deleteButton;

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
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == saleViewButton)
    {
      viewHandler.openView("SaleView");
    }
    /*else if(e.getSource() == deleteButton)
    {
     /* Sale sale = new Sale(); // there are 2 sale constructors, which one should I use?
      modelManager.removeSale(sale);
      updateTableView();
    }
    */

  }
}
