package model;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("Frozen I", "Animation", 13.99, 100, "Elsa Dinh");
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Frozen II", "Animation", 15.89, 110, "Julia Vu");
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Snow White", "Animation", 11.84, 92, "Bella An");
		dvd1.setImgUrl("/assets/images/frozen1.png");
		dvd2.setImgUrl("/assets/images/frozen2.png");
		dvd3.setImgUrl("/assets/images/snow-white.png");
	    
		// Test some methods in class DigitalVideoDisc.
		System.out.println(dvd1.toString());
		System.out.println(dvd1.equals(dvd2)); // false
		
		ArrayList<String> authors1 = new ArrayList<String>();
		authors1.add("Danny Poo");
		Book b1 = new Book("Object-Oriented Programming and Java", "Science", 25.4, authors1);
		b1.setImgUrl("/assets/images/oop-java.png");
		ArrayList<String> authors2 = new ArrayList<String>();
		authors2.add("Global Edition");
		Book b2 = new Book("Fundamentals of Database Systems", "Science", 19.5, authors1);
		b2.setImgUrl("assets/images/database.png");
		
		// Test some methods in class Book.
		b1.addAuthor("Derek Kiong");
		b1.addAuthor("Swarnalatha Ashok");
		System.out.println(b1.toString());
		System.out.println(b2.toString());
		
		Cart cart = new Cart();
		
		// Test some methods in class Cart.
		cart.addMedia(dvd1, 2);
		cart.addMedia(dvd2, 3);
		cart.addMedia(dvd3, 1);
		cart.addMedia(b1, 4);
		cart.addMedia(b2, 3);
		cart.updateQuantity(b2, 2);
		cart.addMedia(b2, 3);
		cart.show();
		cart.searchItemsByTitle("fro");
		cart.searchItemsByTitle("elsa"); // Cannot find
		cart.sortItemsByTitle();
		
		Store store = new Store();
		
		// Test some methods in class Store.
		store.addMedia(dvd1, 10);
		store.addMedia(dvd2, 10);
		store.addMedia(dvd3, 20);
		store.addMedia(b1, 20);
		store.addMedia(b2, 2);
		store.show();
		store.sortItemsByCost();
		store.searchItemsByCategory("ani");
		if (store.checkAvailabilityQuantity(cart)) {
			System.out.println("Your cart is available.");
		}
	}
}
