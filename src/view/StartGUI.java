package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartGUI extends Application
{
  public void start(Stage window)
  {
    VIAPetsModelManager modelManager = new VIAPEtsModelManager("viapets.bin");
    ViewHandler viewHandler = new ViewHandler(window,modelManager);
    viewHandler.start();
  }
}

