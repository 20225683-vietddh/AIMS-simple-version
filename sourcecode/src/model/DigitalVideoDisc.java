package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DigitalVideoDisc extends Disc implements Playable {
	private static int nbDigitalVideoDiscs = 0; 
	
	public DigitalVideoDisc(String title, String category, double cost, int length, String director) {			
		super("DVD" + (++nbDigitalVideoDiscs), title, category, cost, length, director);
	}

	@Override 
	public String toString() {
		return super.toString();
	}
	
	@Override
	public void play() {
		showAlert("Playing a Demo Part", "🎵 DVD '" + this.getTitle() + "' is playing 🎵", AlertType.CONFIRMATION);
		System.out.println("🎵 DVD '" + this.getTitle() + "' is playing 🎵");
	}
	
	private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  
        alert.setContentText(message);
        alert.showAndWait();
    }
}
