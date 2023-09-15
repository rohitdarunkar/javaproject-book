package com.Listmodel;
public class List {
	

	private int id;
	private String Name;
	private String Price;
	private String ImageFileName;
	
	private String Description;
	private String mrp_price;
	
	public String getMrp_price() {
		return mrp_price;
	}
	public void setMrp_price(String mrp_price) {
		this.mrp_price = mrp_price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;
	
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	

	public String getImageFileName() {
		return ImageFileName;
	}
	public void setImageFileName(String imageFileName) {
		ImageFileName = imageFileName;
	}
	private String Category;
	
public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String Price) {
		this.Price = Price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String Category) {
		this.Category = Category;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
