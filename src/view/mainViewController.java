package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import ModelManager.VIAPetsModelManager;

public class mainViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML Button PetsButton;
  @FXML Button ReservationsButton;
  @FXML Button SalesButton;
  @FXML Button CustomersButton;

  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
  }

  public void reset()
  {

  }

  public Scene getScene()
  {
    return scene;
  }

  public void handleActions(ActionEvent e)
  {
    if(e.getSource() == PetsButton)
    {
      viewHandler.openView("PetView");
    }
    else if(e.getSource() == ReservationsButton)
    {
      viewHandler.openView("ReservationView");
    }
    else if (e.getSource() == SalesButton)
    {
      viewHandler.openView("SaleView");
    }
    else if (e.getSource() == CustomersButton)
    {
      viewHandler.openView("ManageCustomerView");
    }
  }


}