package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import javafx.beans.property.ReadOnlyObjectWrapper;


public class ViewCartController {

    @FXML
    private Button backToMainButton;

    @FXML
    private RadioButton byRadioButton;

    @FXML
    private RadioButton byTitleButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button playButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label totalCostLabel;
    
    @FXML
	private TableView<Media> itemsListTable;
    
    @FXML
    private TableColumn<Media, Integer> sttColumn;

    @FXML
    private TableColumn<Media, String> titleColumn;
    
    @FXML
    private TableColumn<Media, String> categoryColumn;

    @FXML
    private TableColumn<Media, Double> costColumn;

    @FXML
    private TableColumn<Media, Integer> quantityColumn;

    @FXML
    private TableColumn<Media, Double> totalColumn;
    
    private Cart cart;
    
    public ViewCartController(Cart cart) {
    	this.cart = cart;
    }
    
    @FXML
    public void initialize() {
    	setupTable();
    	loadCartItems();
    }
    
    private void setupTable() {
        sttColumn.setCellValueFactory(cellData -> 
            new ReadOnlyObjectWrapper<>(itemsListTable.getItems().indexOf(cellData.getValue()) + 1)
        );

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        quantityColumn.setCellValueFactory(cellData -> {
            Media media = cellData.getValue();
            Integer quantity = cart.getItemsOrdered().get(media);
            return new ReadOnlyObjectWrapper<>(quantity);
        });

        // Total = cost * quantity
        totalColumn.setCellValueFactory(cellData -> {
            Media media = cellData.getValue();
            Integer quantity = cart.getItemsOrdered().get(media);
            Double totalCost = media.getCost() * quantity;
            return new ReadOnlyObjectWrapper<>(totalCost);
        });
    }
    
    private void loadCartItems() {
        itemsListTable.getItems().setAll(cart.getItemsOrdered().keySet());
    }
}
