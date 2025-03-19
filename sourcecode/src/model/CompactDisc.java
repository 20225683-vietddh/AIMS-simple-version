package model;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>(); 
	private static int nbCompactDiscs = 0;
	
	public CompactDisc(String title, String category, double cost, int length, String director, String artist, ArrayList<Track> tracks) {
		super("CD" + (++nbCompactDiscs), title, category, cost, length, director);
		this.artist = artist;
		this.tracks = tracks;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public void addTrack(Track track) {
		if (!tracks.contains(track)) {
			tracks.add(track);
		}
	}
	
	public void removeTrack(Track track) {
		if (tracks.contains(track)) {
			tracks.remove(track);
		}
	}
	
	@Override 
	public String toString() {
		return super.toString() +
				"ARTIST: " + this.artist + "\n" +
				"TRACKS: " + this.tracks.toString() + "\n";
	}
	
	@Override 
	public void play() {
		showAlert("Playing a Demo Part", "🎵 CD '" + this.getTitle() + "' is playing 🎵", AlertType.CONFIRMATION);
		System.out.println("🎵 CD '" + this.getTitle() + "' is playing 🎵");
	}
	
	private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  
        alert.setContentText(message);
        alert.showAndWait();
    }
}
