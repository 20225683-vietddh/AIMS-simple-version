package model;

public class Track implements Playable {
	private String title;
	private int length;
	
	public Track(String title, int length) {
		this.title = title;
		this.length = length;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public boolean equals(Track anotherTrack) {
		return this.title.equals(anotherTrack.title) && this.length == anotherTrack.length ? true : false;
	}
	
	@Override 
	public void play() {
		System.out.println("Track '" + this.getTitle() + "' is playing.");
	}
}
