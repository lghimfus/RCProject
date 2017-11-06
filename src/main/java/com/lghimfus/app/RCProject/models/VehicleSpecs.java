package com.lghimfus.app.RCProject.models;


/**
 * This class is a model object that contains and gives access to the data of 
 * a vehicle's specification.
 * 
 * @author lghimfus
 */

public class VehicleSpecs {
	private String carType;
	private String doorsType;
	private String transmission;
	private String fuel;
	private String airCon;
	private int score;
	
	public VehicleSpecs(String carType, String doorsType, String transmission,
	                    String fuel, String airCon) {
	  this.carType = carType;
	  this.doorsType = doorsType;
	  this.transmission = transmission;
	  this.fuel = fuel;
	  this.airCon = airCon;
	}

  public String getCarType() {
    return carType;
  }

  public String getDoorsType() {
    return doorsType;
  }

  public String getTransmission() {
    return transmission;
  }

  public String getFuel() {
    return fuel;
  }

  public String getAirCon() {
    return airCon;
  }

  public int getScore() {
    return score;
  }
	
  public void setScore(int score) {
    this.score = score;
  }
  
}
