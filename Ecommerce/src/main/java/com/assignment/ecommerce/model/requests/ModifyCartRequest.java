package com.assignment.ecommerce.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModifyCartRequest {

	@JsonProperty
	private String contactNumber;

	@JsonProperty
	private long itemId;

	@JsonProperty
	private int quantity;

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
