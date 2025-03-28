package model;

public abstract class Disc extends Media {
	private int length;
	private String director;
	
	public Disc(String id, String title, String category, double cost, int length, String director) {
		super(id, title, category, cost);
		this.length = length;
		this.director = director;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	@Override 
	public String toString() {
		return super.toString() +
				"LENGTH: " + "[" +this.length + "s" + "]" +"\n" +
				"DIRECTOR: " + "[" + this.director + "]" +"\n";
	}
}
