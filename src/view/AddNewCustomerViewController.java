package view;

import ModelManager.VIAPetsModelManager;
import javafx.scene.Scene;

public class AddNewCustomerViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

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

  public void reset() {}
}

