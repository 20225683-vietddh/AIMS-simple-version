package controller;

import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import java.util.Optional;

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
    	displayTotalCostLabel();
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
        
        itemsListTable.setOnMouseClicked(event -> {
        	Media media = itemsListTable.getSelectionModel().getSelectedItem();
            if (media != null) {
                removeButton.setVisible(true);
                playButton.setVisible(media instanceof Playable);
            } else {
            	removeButton.setVisible(false);
            	playButton.setVisible(false);
            }
        });
    }
    
    public void handlePlay() {
    	Media media = itemsListTable.getSelectionModel().getSelectedItem();
    	if (media instanceof Playable) {
    		((Playable) media).play();
    	}
    }
    
    public void handleRemove() {
    	Media media = itemsListTable.getSelectionModel().getSelectedItem();
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRM DELETE");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete '" + media.getTitle() + "' from the current cart?");

        Optional<ButtonType> result = alert.showAndWait();

    	if (result.isPresent() && result.get() == ButtonType.OK) {
    		boolean isSuccessful = cart.removeMedia(media);
        	if (isSuccessful) {
        		itemsListTable.getItems().remove(media);
        	}
        	
        	removeButton.setVisible(false);
        	playButton.setVisible(false);
        	displayTotalCostLabel();
    	}
    	
    	cart.show(); // Print the current cart in console log to find bug
    }
    
    private void loadCartItems() {
        itemsListTable.getItems().setAll(cart.getItemsOrdered().keySet());
    }
    
    private void displayTotalCostLabel() {
    	double totalCost = cart.totalCost();
    	totalCostLabel.setText(String.format("%.2f$", totalCost));
    }
}
