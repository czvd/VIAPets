import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class Controller
{
  @FXML
  private HBox secondaryRow; // HBox for Add and Manage buttons

  @FXML
  private void handleCustomersButtonClick() {
    // Toggle visibility of the secondary row
    boolean isVisible = secondaryRow.isVisible();
    secondaryRow.setVisible(!isVisible);
    secondaryRow.setManaged(!isVisible);
  }
}
