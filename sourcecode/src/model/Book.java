package model;

import java.util.ArrayList;

public class Book extends Media {
	private ArrayList<String> authors = new ArrayList<String>();
	private static int nbBooks = 0;
	
	public Book(String title, String category, double cost, ArrayList<String> authors) {
		super("BOOK" + (++nbBooks), title, category, cost);
		this.authors = authors;
	}
	
	public void addAuthor(String authorName) {
		if (!authors.contains(authorName)) {
			authors.add(authorName);
		}
	}
	
	public void removeAuthor(String authorName) {
		if (authors.contains(authorName)) {
			authors.remove(authorName);
		}
	}
	
	@Override
	public String toString() {
		return super.toString() +
				"AUTHORS: " + authors.toString() + "\n"; 
	}
}
