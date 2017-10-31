package com.lghimfus.app.RCProject.models;

public class Vehicle {
	private String sipp;
	private String name;
	private Double price;
	private String supplier;
	private Double rating;

	public String getSipp() {
		return sipp;
	}

	public void setSipp(String sipp) {
		this.sipp = sipp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(name).append(" - ")
								.append(sipp).append(" - ")
								.append(supplier).append(" - ")
								.append(price).append(" - ")
								.append(rating).toString();
	}
}
