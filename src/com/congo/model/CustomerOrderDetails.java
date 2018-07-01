package com.congo.model;

import java.util.Date;

public class CustomerOrderDetails {
	private int orderId;
	private int custId;
	private Date orderDate;
	private float orderTotal;
	private int recordingId;
	private float price;
	private int orderQuantity;
	private float itemTotal;
	private String artistName;
	private String title;
	private String category;
	private int numTracks;
	
	public CustomerOrderDetails() {
		super();
	}

	public CustomerOrderDetails(int orderId, int custId, Date orderDate, float orderTotal, int recordingId, float price,
			int orderQuantity, float itemTotal, String artistName, String title, String category, int numTracks) {
		super();
		this.orderId = orderId;
		this.custId = custId;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.recordingId = recordingId;
		this.price = price;
		this.orderQuantity = orderQuantity;
		this.itemTotal = itemTotal;
		this.artistName = artistName;
		this.title = title;
		this.category = category;
		this.numTracks = numTracks;
	}

	@Override
	public String toString() {
		return "CustomerOrders [orderId=" + orderId + ", custId=" + custId + ", orderDate=" + orderDate
				+ ", orderTotal=" + orderTotal + ", recordingId=" + recordingId + ", price=" + price
				+ ", orderQuantity=" + orderQuantity + ", itemTotal=" + itemTotal + ", artistName=" + artistName
				+ ", title=" + title + ", category=" + category + ", numTracks=" + numTracks + "]";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getRecordingId() {
		return recordingId;
	}

	public void setRecordingId(int recordingId) {
		this.recordingId = recordingId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public float getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(float itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNumTracks() {
		return numTracks;
	}

	public void setNumTracks(int numTracks) {
		this.numTracks = numTracks;
	}	
	
}
