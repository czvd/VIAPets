package view;

import ModelManager.VIAPetsModelManager;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.NoSuchElementException;

public class ReservationViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button backButton;
  @FXML private Button addNewButton;
  @FXML private Button deleteButton;

  //initializing sale table
  @FXML private TableView<KennelReservation> KennelTableView = new TableView<>();
  @FXML private TableColumn<KennelReservation, String> customerColumn = new TableColumn<>("CustomerColumn");
  @FXML private TableColumn<KennelReservation, String> costumerNameColumn = new TableColumn<>("CostumerNameColumn");
  @FXML private TableColumn<KennelReservation, String> costumerPhoneColumn = new TableColumn<>("CostumerPhoneColumn");
  @FXML private TableColumn<KennelReservation, String> startDateColumn = new TableColumn<>("StartDateColumn");
  @FXML private TableColumn<KennelReservation, String> endDateColumn = new TableColumn<>("EndDateColumn");
  @FXML private TableColumn<KennelReservation, String> priceColumn = new TableColumn<>("PriceColumn");
  private Pet pet;
  private Customer customer;
  private Date start;
  private Date end;
  private double price;
  private KennelReservation selectedReservation;

  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    //reservation table;

    customerColumn.getColumns().addAll(costumerNameColumn,costumerPhoneColumn);
    KennelTableView.getColumns().addAll(customerColumn,startDateColumn,endDateColumn,priceColumn);
    costumerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    costumerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    customerColumn.setSortable(false);
    costumerNameColumn.setSortable(false);
    costumerPhoneColumn.setSortable(false);
    startDateColumn.setSortable(false);
    endDateColumn.setSortable(false);
    priceColumn.setSortable(false);
    updateTable();
    TableView.TableViewSelectionModel<KennelReservation> selectionModel =
        KennelTableView.getSelectionModel();
    ObservableList<KennelReservation> selectedItems =
        selectionModel.getSelectedItems();

    selectedItems.addListener(
        new ListChangeListener<KennelReservation>() {
          @Override
          public void onChanged(
              Change<? extends KennelReservation> change) {
            try
            {
              KennelReservation temp = change.getList().getFirst();
              if (temp != null)
              {

                switch (temp.getPet())
                {
                  case Cat cat -> pet = new Cat(cat);
                  case Fish fish -> pet = new Fish(fish);
                  case Dog dog -> pet = new Dog(dog);
                  case Rodent rodent -> pet = new Rodent(rodent);
                  case Bird bird -> pet = new Bird(bird);
                  case Various various -> pet = new Various(various);
                  default -> System.err.println("Pet tpe is not correct");
                }
                customer = temp.getCustomer();
                start = temp.getStartDate();
                end = temp.getEndDate();
                price = temp.getFinalPrice();
                selectedReservation = new KennelReservation(pet,customer,start,end,price);
              }
            }
            catch (NullPointerException e)
            {
              System.out.println("no elements found");
            }
            catch (NoSuchElementException e)
            {
              System.out.println("petTableView, pet changed");
            }
          }
        });
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
      viewHandler.openView("AddReservationView");
    }
    else if(e.getSource() == deleteButton)
    {
      if (selectedReservation!=null)
      {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Do you really want to delete it?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Back");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
          modelManager.removeReservation(selectedReservation);
        }
      }

    }
  }

  public void updateTable()
  {
    if (KennelTableView != null)
    {
      KennelTableView.getItems().clear();
    }

    KennelReservationList reservations = modelManager.getAllReservations();
    for (int i = 0; i < reservations.size(); i++)
    {
      KennelTableView.getItems().add(reservations.get(i));
    }
  }

}
