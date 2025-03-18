package controller;

import model.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.geometry.Pos;

public class MainMenuController {

    @FXML
    private Button loginButton;

    @FXML
    private GridPane gpPopularList;
    
    @FXML
    private TextArea searchBar;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private TextField searchTextField;
    
    @FXML
    private Button viewCartButton;
    
    @FXML
    private Label itemsInCartLabel;
    
    private Cart cart = new Cart();
    
    private int itemsInCartCount = 0;
    
    public void handleLogIn() {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("Hello");
    	alert.setHeaderText("This is a test message");
    	alert.setContentText("You've clicked the buutton.");
    	alert.showAndWait();
    }
    
    public void handleAddToCart(Media media) {
    	TextInputDialog dialog = new TextInputDialog("1");
    	dialog.setTitle("Add to Cart");
    	dialog.setHeaderText("Add New Item to Cart");
    	dialog.setContentText("Please enter the quantity:");
    	
    	Optional<String> result = dialog.showAndWait();
    	
    	if (result.isPresent()) {
    		try {
    			int quantity = Integer.parseInt(result.get());
    			if (quantity <= 0) {
    				showAlert("ERROR", "❌ Invalid quantity!", Alert.AlertType.ERROR);
    				return;
    			}
    			
    			cart.addMedia(media, quantity);
    			showAlert("🎉 Add to Cart Successfully", quantity + " item(s) '" + media.getTitle() + "' has/have been added to cart!", Alert.AlertType.INFORMATION);
    			this.itemsInCartCount += 1;
    			updateItemsInCartLabel();
    			cart.show();
    		} catch (NumberFormatException e) {
    			showAlert("ERROR", "❌ Not a number. Please enter a positive integer!", Alert.AlertType.ERROR);
    		}
    	}
    }
    
    private HBox createMediaItem(Media media) {
    	HBox itemContainer = new HBox(10);
    	itemContainer.setPrefSize(200, 250);
    	itemContainer.getStyleClass().add("inner-box-item");
    	itemContainer.setAlignment(Pos.CENTER_LEFT);
    	
    	ImageView imageView = new ImageView(new Image(media.getImgUrl()));
    	imageView.setFitWidth(100);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(50);
        imageView.setLayoutY(10);
        
        Label titleLabel = new Label(media.getTitle());
        titleLabel.setLayoutX(50);
        titleLabel.setLayoutY(90);
        titleLabel.getStyleClass().add("inner-box-item-title");
        
        Label costLabel = new Label("$" + media.getCost());
        costLabel.setLayoutX(50);
        costLabel.setLayoutY(120);
        costLabel.setId("inner-box-item-sub-info");
        
        Label categoryLabel = new Label(media.getCategory());
        categoryLabel.setLayoutX(50);
        categoryLabel.setLayoutY(150);
        categoryLabel.setId("inner-box-item-sub-info");
        
        Label quantityLabel = new Label("Current quantity: " + media.getQuantity());
        quantityLabel.setLayoutX(50);
        quantityLabel.setLayoutY(180);
        quantityLabel.setId("inner-box-item-sub-info");
        
        Button buyButton = new Button("Add to cart");
        buyButton.getStyleClass().add("inner-box-item-buy-button");
        buyButton.setOnAction(event -> handleAddToCart(media));
        
        Button viewButton = new Button("View detail");
        viewButton.getStyleClass().add("inner-box-item-view-button");
        
        HBox buttonContainer = new HBox(10, buyButton, viewButton);
        buttonContainer.setAlignment(Pos.CENTER_LEFT);
        buttonContainer.setStyle("-fx-padding: 10px 0 0 0");
        
        VBox infoBox = new VBox(0);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(titleLabel, costLabel, categoryLabel, quantityLabel, buttonContainer);
        
        itemContainer.getChildren().addAll(imageView, infoBox);
        
        return itemContainer;
    }
    
    private void popularGridPane(Store store) {
    	gpPopularList.getChildren().clear(); 
    	
        int row = 0, col = 0;
        for (Media media : store.getItemsInStore()) {
            HBox itemPane = createMediaItem(media);
            
            // Add AnchorPane to GridPane add position (col, row)
            gpPopularList.add(itemPane, col, row);
            
            col++;
            if (col == 3) { // If 3 columns are filled, move to new row
                col = 0;
                row++;
            }
        }
    }
    
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void updateItemsInCartLabel() {
        itemsInCartLabel.setText(String.valueOf(itemsInCartCount));
    }
    
    @FXML
    public void initialize() {
    	Store store = new Store();
    	DigitalVideoDisc dvd1 = new DigitalVideoDisc("Frozen I", "Animation", 13.99, 100, "Walt Disney");
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Frozen II", "Animation", 15.89, 110, "Walt Disney");
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Snow White", "Animation", 11.84, 92, "Walt Disney");
		DigitalVideoDisc dvd4 = new DigitalVideoDisc("Doraemon Collections", "Animation", 14.02, 121, "Fujiko Fujio");
		DigitalVideoDisc dvd5 = new DigitalVideoDisc("Lion King I", "Animation", 12.33, 129, "Rob Minkoff");
		dvd1.setImgUrl("/assets/images/frozen1.png");
		dvd2.setImgUrl("/assets/images/frozen2.png");
		dvd3.setImgUrl("/assets/images/snow-white.png");
		dvd4.setImgUrl("/assets/images/doraemon.png");
		dvd5.setImgUrl("/assets/images/lion-king.png");
		
		ArrayList<String> authors1 = new ArrayList<String>();
		authors1.add("Danny Poo");
		Book b1 = new Book("Object-Oriented Programming and Java", "Science", 25.4, authors1);
		b1.setImgUrl("/assets/images/oop-java.png");
		b1.addAuthor("Derek Kiong");
		b1.addAuthor("Swarnalatha Ashok");
		
		ArrayList<String> authors2 = new ArrayList<String>();
		authors2.add("Global Edition");
		Book b2 = new Book("Fundamentals of Database Systems", "Science", 19.5, authors2);
		b2.setImgUrl("assets/images/database.png");
		
		ArrayList<String> authors3 = new ArrayList<String>();
		authors3.add("Usui Yoshito");
		Book b3 = new Book("Cray on Shin Chan ep.10", "Comic book", 1.2, authors3);
		b3.setImgUrl("assets/images/shin.png");
		
		ArrayList<Track> tracks1 = new ArrayList<Track>();
		Track track1 = new Track("Emotion", 219);
		Track track2 = new Track("Hero", 221);
		tracks1.add(track1);
		tracks1.add(track2);
		CompactDisc cd1 = new CompactDisc("Daydream", "Music", 10.5, 1109, "Sony", "Mariah Carey", tracks1);
		cd1.setImgUrl("assets/images/daydream.png");
		
		store.addMedia(dvd1, 10);
		store.addMedia(dvd2, 10);
		store.addMedia(dvd3, 10);
		store.addMedia(dvd4, 20);
		store.addMedia(dvd5,  5);
		store.addMedia(b1, 15);
		store.addMedia(b2, 10);
		store.addMedia(b3, 5);
		store.addMedia(cd1, 20);
		
        popularGridPane(store); 
        
        itemsInCartLabel.setText("0");
    }
}
