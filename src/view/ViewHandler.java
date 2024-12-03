package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private MainViewController mainViewController;
  private AllStudentsViewController allStudentsViewController;
  private ChangeCountryViewController changeCountryViewController;

  private VIAPetsModelManager modelManager;

  public ViewHandler(Stage stage, VIAPetsModelManager modelManager)//StudentModelManager modelManager
  {
    this.stage = stage;
    this.modelManager = modelManager;
  }

  public void start()
  {
    loadViewMain();
    loadViewAllStudents();
    loadViewChangeCountry();
    openView("MainView");
  }

  public void openView(String id)
  {
    switch (id)
    {
      case "MainView":
        stage.setScene(mainViewController.getScene());
        mainViewController.reset();
        break;
      case "AllStudentsView":
        stage.setScene(allStudentsViewController.getScene());
        allStudentsViewController.reset();
        break;
      case "ChangeCountryView":
        stage.setScene(changeCountryViewController.getScene());
        changeCountryViewController.reset();
        break;
    }

    String title = "";

    if(stage.getScene().getRoot().getUserData() !=null)
    {
      title = stage.getScene().getRoot().getUserData().toString();
    }

    stage.setTitle(title);
    stage.show();
  }

  private void loadViewMain()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("MainView.fxml"));
      Region root = loader.load();
      mainViewController = loader.getController();
      mainViewController.init(this, new Scene(root));//modelManager
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewAllStudents()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AllStudentsView.fxml"));
      Region root = loader.load();
      allStudentsViewController = loader.getController();
      allStudentsViewController.init(this, new Scene(root));//modelManager
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewChangeCountry()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ChangeCountryView.fxml"));
      Region root = loader.load();
      changeCountryViewController = loader.getController();
      changeCountryViewController.init(this, new Scene(root));//modelManager
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /*
    It's not necessary in this example, but sometimes it might be needed
    for one controller to access/modify data in another controller's view.
    That can be done by creating a get-method in this ViewHandler class for
    the controller of the view that must be accessed. E.g.:

     public AllStudentsViewController getAllStudentsViewController()
     {
       return allStudentsViewController;
     }

    It's then possible in e.g. the MainViewController to call this
    get-method on its ViewHandler object, to get access to any methods
    made in the AllStudentsViewController. E.g.:

     viewHandler.getAllStudentsViewController().aSetMethod("New data to set");
  */
}
