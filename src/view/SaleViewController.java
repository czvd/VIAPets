package view;

import ModelManager.VIAPetsModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Sale;

public class SaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;
  //private Region root;


  @FXML private Button backButton;
  @FXML private Button deleteButton;
  @FXML private Button addNewButton;

  // Initialize the controller with required dependencies
  public void init(ViewHandler viewHandler,Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    //this.root = root;
  }

  // Getter for the scene
  public Scene getScene() {
    return scene;

  }

//  public Region getRoot()
//  {
//    return root;
//  }

  // Reset method for refreshing the view
  public void reset()
  {
    // Implement logic to refresh SaleView (if needed)
  }

  public void handleActions(ActionEvent e)
  {
    if(e.getSource()== backButton)
    {
      viewHandler.openView("MainView");
    }
    else if(e.getSource()== addNewButton)
    {
      viewHandler.openView("AddSaleView");
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
