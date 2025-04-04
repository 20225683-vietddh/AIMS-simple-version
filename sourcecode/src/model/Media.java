package model;

import java.util.*;

public abstract class Media {
	private String id;
	private String title;
	private String category;
	private double cost;
	private String imgUrl;
	private int quantity;
	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
	public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
	
	public Media(String id, String title, String category, double cost) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public String getImgUrl() {
		return this.imgUrl;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public String toString() {
		return "ID: " + "[" + this.id + "]" + "\n" +
				"TITLE: " + "[" + this.title + "]" + "\n" +
				"CATEGORY: " + "[" + this.category + "]" + "\n" +
				"COST: " + "[" + this.cost + "$" + "]" + "\n";
	}
	
	public boolean equals(Media anotherMedia) {
		return this.title.equals(anotherMedia.getTitle()) ? true : false;
	}
	
	public boolean isTitleMatched(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return false;
		}
		
		String lowerCaseKeyword = keyword.toLowerCase();
		String lowerCaseTitle = title.toLowerCase();
		
		return lowerCaseTitle.contains(lowerCaseKeyword);
	}
	
	public boolean isCategoryMatched(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return false;
		}
		
		String lowerCaseKeyword = keyword.toLowerCase();
		String lowerCaseCategory = category.toLowerCase();
		
		return lowerCaseCategory.contains(lowerCaseKeyword);
	}
}
