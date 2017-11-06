package com.lghimfus.app.RCProject.models;

/**
 * This class is a model object that contains and gives access to the data of 
 * a vehicle.
 * 
 * @author lghimfus
 */
public class Vehicle {
	private String sipp;
	private String name;
	private Double price;
	private String supplier;
	private Double rating;
	private VehicleSpecs vehicleSpecs;
	
	public Vehicle () {
	  
	}
	
  public Vehicle(String sipp, String name, double price, String supplier, double rating) {
    this.sipp = sipp;
    this.name = name;
    this.supplier = supplier;
    this.price = price;
    this.rating = rating;
  }
	
	public String getSipp() {
    return sipp;
  }
  
  public String getName() {
    return name;
  }
  
  public Double getPrice() {
    return price;
  }

  public String getSupplier() {
    return supplier;
  }
  
  public Double getRating() {
    return rating;
  }
  
  public VehicleSpecs getVehicleSpecs() {
    return vehicleSpecs;
  }
  
  public void setVehicleSpecs(VehicleSpecs vehicleSpecs) {
    this.vehicleSpecs = vehicleSpecs;
  }
	
}
