package com.lghimfus.app.RCProject.models;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is a model object that contains and gives access to the data of 
 * a SIPP code.
 * 
 * @author lghimfus
 *
 */
public class SippSpecs {
	
	Map<Character, LetterSpecsWrapper> carTypesMap;
	Map<Character, LetterSpecsWrapper> doorTypesMap;
	Map<Character, LetterSpecsWrapper> transmissionTypesMap;
	Map<Character, LetterSpecsWrapper> fuelAcMap;
	
	public SippSpecs() {
		this.carTypesMap = new HashMap<>();
		this.doorTypesMap = new HashMap<>();
		this.transmissionTypesMap = new HashMap<>();
		this.fuelAcMap = new HashMap<>();
	}

	public Map<Character, LetterSpecsWrapper> getCarTypesMap() {
		return carTypesMap;
	}

	public void setCarTypesMap(Map<Character, LetterSpecsWrapper> carTypesMap) {
		this.carTypesMap = carTypesMap;
	}

	public Map<Character, LetterSpecsWrapper> getDoorTypesMap() {
		return doorTypesMap;
	}

	public void setDoorTypesMap(Map<Character, LetterSpecsWrapper> doorTypesMap) {
		this.doorTypesMap = doorTypesMap;
	}

	public Map<Character, LetterSpecsWrapper> getTransmissionTypesMap() {
		return transmissionTypesMap;
	}

	public void setTransmissionTypesMap(Map<Character, LetterSpecsWrapper> transmissionTypesMap) {
		this.transmissionTypesMap = transmissionTypesMap;
	}

	public Map<Character, LetterSpecsWrapper> getFuelAcMap() {
		return fuelAcMap;
	}

	public void setFuelAcMap(Map<Character, LetterSpecsWrapper> fuelAcMap) {
		this.fuelAcMap = fuelAcMap;
	}

	/**
	 * This class is used to wrap up the specifications of a certain SIPP letter.
	 *  
	 */
	public static class LetterSpecsWrapper {
		private String value;
		private int score;
		
		public LetterSpecsWrapper() {
		}

		public String getValue() {
			return value;
		}

		public int getScore() {
			return score;
		}
		
	}
}
