package com.TechM.Cart_Service.DTO;

public class CartItemRequestDto {
    private Long productId;  // Product ID
    private String productName;  // Product name
    private int quantity;  // Quantity of the product
    private double price;  // Price of the product
    
    
    
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
    
    
    
}

