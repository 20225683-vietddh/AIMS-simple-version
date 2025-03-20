package model;

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
		System.out.println("🎵 DVD '" + this.getTitle() + "' is playing 🎵");
	}
}
