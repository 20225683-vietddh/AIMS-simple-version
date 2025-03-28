package model;

import java.util.*;

public class Cart {
	private Map<Media, Integer> itemsOrdered = new HashMap<Media, Integer>();
	final int MAX_NUMBER_ORDERED = 20;
	
	public Map<Media, Integer> getItemsOrdered() {
		return this.itemsOrdered;
	}
	
	public boolean addMedia(Media media, int quantity) {
		if (quantity <= 0) {
			System.out.println("Invalid quantity!");
			return false;
		}
		
		if (isCartLimitExceeded(quantity)) {
			System.out.println("❌ Cannot add " + quantity + " item(s) '" + media.getTitle() + "'. The cart will be over 20 items.");
			return false;
		}
		
		itemsOrdered.put(media, itemsOrdered.getOrDefault(media, 0) + quantity);
		System.out.println("Added to Cart Successfully 🎉 " + quantity + " item(s) '" + media.getTitle() + "' has/have been added to cart!");
		return true;
	}
	
	public boolean removeMedia(Media media) {
		if (media == null) {
			System.out.println("Invalid media");
			return false;
		}
		
		if (!itemsOrdered.containsKey(media)) {
			System.out.println("❌ Item '" + media.getTitle() + "' does not exist in the current cart.");
			return false;
		}
		
		itemsOrdered.remove(media);
		System.out.println("Removed Successfully 🎉 '" + media.getTitle() + "' has/have been removed from cart!");	
		return true;
	}
	
	public void updateQuantity(Media media, int newQuantity) {
		if (media == null || newQuantity < 0) {
			System.out.println("❌ Invalid item or invalid quantity.");
			return;
		}
		
		if(!itemsOrdered.containsKey(media)) {
			System.out.println("❌ Item '" + media.getTitle() + "'does not exist in current cart.");
			return;
		}
		
		if (newQuantity == 0) {
			this.removeMedia(media);
			return;
		}
		
		int currentTotal = getTotalItems() - itemsOrdered.get(media) + newQuantity;
		if (currentTotal > MAX_NUMBER_ORDERED) {
			System.out.println("❌ Cannot update the quantity of '" + media.getTitle() + "' to " + newQuantity + ". The cart will be full.");
			return;
		}
		
		itemsOrdered.put(media, newQuantity);
		System.out.println("Updated: '" + media.getTitle() + "' to " + newQuantity + "items.");
	}
	
	public double totalCost() {
		double total = 0;
		for (Map.Entry<Media, Integer> entry : itemsOrdered.entrySet()) {
			double cost = entry.getKey().getCost();
			int quantity = entry.getValue();
			total += cost * quantity;
		}
		return total;
	}
	
	public void searchItemsByTitle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			System.out.println("❌ Keyword is invalid");
			return;
		}
		
		boolean found = false;
		
		System.out.println("🔍 See the result(s) when you search for '" + keyword + "':");
		
		for (Map.Entry<Media, Integer> entry : itemsOrdered.entrySet()) {
			Media media = entry.getKey();
			String title = media.getTitle();
			double cost = media.getCost();
			int quantity = entry.getValue();
			
			if (media.isTitleMatched(keyword)) {
				System.out.println("- " + title + " (Cost: " + cost + "$, Quantity: " + quantity + ") => Total: " + cost * quantity + "$");
				found = true;
			}
		}
		
		if (!found) {
			System.out.println("❌ Cannot find any item that contains '" + keyword + "' in the current cart.");
		}
	}
	
	public void sortItemsByTitle() {
		if (itemsOrdered.isEmpty()) {
			System.out.println("❌ The current cart is empty!");
			return;
		}
		
		List<Media> sortedList = new ArrayList<>(itemsOrdered.keySet());
		sortedList.sort(Media.COMPARE_BY_TITLE_COST);
		
		System.out.println("📋 List of items in cart after being sorted by title:");
		for (Media media : sortedList) {
			String title = media.getTitle();
			double cost = media.getCost();
			int quantity = itemsOrdered.get(media);
			System.out.println("- " + title + "(Cost: " + cost + ", Quantity: " + quantity + ") => Total: " + cost * quantity);
		}
	}
	
	public void show() {
		System.out.println();
		System.out.println("=== THE CURRENT CART ===");
		for (Map.Entry<Media, Integer> entry : itemsOrdered.entrySet() ) {
			double cost = entry.getKey().getCost();
			int quantity = entry.getValue();
			System.out.println("- " + entry.getKey().getTitle() + " (Cost: " + cost + "$, Quantity: " + entry.getValue() + ") => Total: " + cost * quantity + "$");
		}
		System.out.println("TOTAL COST: " + this.totalCost());
		System.out.println();
	}
	
	public int getTotalItems() {
		int total = 0;
		for (int quantity : itemsOrdered.values()) {
			total += quantity;
		}
		return total;
	}
	
	public boolean isCartLimitExceeded(int quantity) {
		return getTotalItems() + quantity > MAX_NUMBER_ORDERED;
	}
	
	public boolean isEmpty() {
		return this.itemsOrdered.isEmpty();
	}
}
