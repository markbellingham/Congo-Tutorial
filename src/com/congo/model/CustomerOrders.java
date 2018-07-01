package com.congo.model;

import java.util.Date;

public class CustomerOrders {
	private int orderId;
	private Date orderDate;
	private float orderTotal;
	
	public CustomerOrders() {
		super();
	}

	public CustomerOrders(int orderId, Date orderDate, float orderTotal) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
	}

	@Override
	public String toString() {
		return "CustomerOrders [orderId=" + orderId + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal + "]";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	
}
