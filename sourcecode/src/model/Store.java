package model;

import java.util.*;

public class Store {
	private List<Media> itemsInStore = new ArrayList<Media>();
	
	public List<Media> getItemsInStore() {
		return this.itemsInStore;
	}
	
	public void addMedia(Media media, int quantity) {
		if (media == null || quantity <= 0) {
			System.out.println("❌ Invalid media or quantity!");
			return;
		}
		
		for (Media item : itemsInStore) {
	        if (item.equals(media)) {
	            System.out.println("❌ Item '" + media.getTitle() + "' is already in the store.");
	            return;
	        }
	    }
		
		itemsInStore.add(media);
		media.setQuantity(quantity);
		System.out.println("✅ Added '" + media.getTitle() + "' with quantity " + quantity + ".");
	}
	
	public void removeMedia(Media media) {
		if (media == null) {
			System.out.println("❌ Invalid media!");
			return;
		}
		
		for (Media item : itemsInStore) {
	        if (!item.equals(media)) {
	            System.out.println("❌ Item '" + media.getTitle() + "' is not in the current store.");
	            return;
	        }
	    }
		
		media.setQuantity(0);
		itemsInStore.remove(media);
		System.out.println("✅ Removed '" + media.getTitle() + " from store.");
	}
	
	public void searchItemsByTitle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			System.out.println("❌ Keyword is invalid");
			return;
		}
		
		boolean found = false;
		
		System.out.println("🔍 See the result(s) when you search for '" + keyword + "':");
		
		for (Media media : itemsInStore) {
			int quantity = media.getQuantity();
			if (media.isTitleMatched(keyword)) {
				System.out.println(media.toString() + "CURRENT QUANTITY: " + quantity + "\n");
				found = true;
			}
		}
		
		if (!found) {
			System.out.println("❌ Cannot find any item that contains '" + keyword + "' in title in the current store.");
		}
	}
	
	public void searchItemsByCategory(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			System.out.println("❌ Keyword is invalid");
			return;
		}
		
		boolean found = false;
		
		System.out.println("🔍 See the result(s) when you search for '" + keyword + "':");
		
		for (Media media : itemsInStore) {
			int quantity = media.getQuantity();
			if (media.isCategoryMatched(keyword)) {
				System.out.println(media.toString() + "CURRENT QUANTITY: " + quantity + "\n");
				found = true;
			}
		}
		
		if (!found) {
			System.out.println("❌ Cannot find any item that contains '" + keyword + "' in category in the current store.");
		}
	}
	
	public void sortItemsByTitle() {
		if (itemsInStore.isEmpty()) {
			System.out.println("❌ The current store is empty!");
			return;
		}
		
		List<Media> sortedList = new ArrayList<>(itemsInStore);
		sortedList.sort(Media.COMPARE_BY_TITLE_COST);
		
		System.out.println("📋 ALL ITEMS IN THE CURRENT STORE AFTER SORTED BY TITLE");
		for (Media media : sortedList) {
			System.out.println(media.toString() + "CURRENT QUANTITY " + media.getQuantity());
			System.out.println("---");
		}
		System.out.println();
	}
	
	public void sortItemsByCost() {
		if (itemsInStore.isEmpty()) {
			System.out.println("❌ The current store is empty!");
			return;
		}
		
		List<Media> sortedList = new ArrayList<>(itemsInStore);
		sortedList.sort(Media.COMPARE_BY_COST_TITLE);
		
		System.out.println("📋 ALL ITEMS IN THE CURRENT STORE AFTER SORTED BY COST");
		for (Media media : sortedList) {
			System.out.println(media.toString() + "CURRENT QUANTITY " + media.getQuantity());
			System.out.println("---");
		}
		System.out.println();
	}
	
	public boolean checkAvailabilityQuantity(Cart cart) {
		Map<Media, Integer> itemsInCart = cart.getItemsOrdered();
		
		for (Media media : itemsInCart.keySet()) {
			int quantityInCart = itemsInCart.get(media);
			int quantityInStore = media.getQuantity();
			
			if (quantityInCart > quantityInStore) {
				System.out.println("❌ The current quantity in store (" + quantityInStore + ") of item '" + media.getTitle() + "' cannot meet the demand (" + quantityInCart + ") in cart.");
				return false;
			}
		}
		
		return true;
	}
	
	public void show() {
		System.out.println();
		System.out.println("=== ALL ITEMS IN THE CURRENT STORE ===");
		for (Media media : itemsInStore) {
			System.out.println(media.toString() + "CURRENT QUANTITY: " + media.getQuantity());
			System.out.println("---");
		}
		System.out.println();
	}
}
