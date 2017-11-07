package com.lghimfus.app.RCProject.services;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lghimfus.app.RCProject.models.SippSpecs;
import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.models.SippSpecs.LetterSpecsWrapper;

/**
 * This class consists of methods that handles requests related to a car's SIPP.
 * 
 * @author lghimfus
 *
 */
public class SippSpecsService{
  
  private final static String CAR_TYPES_PATH = "json/car_types.json";
  private final static String DOOR_TYPES_PATH = "json/door_types.json";
  private final static String TRANSMISSION_TYPES_PATH = "json/transmission_types.json";
  private final static String FUEL_AC_TYPES_PATH = "json/fuel_ac_types.json";
  
  private static final int CAR_TYPE_POS = 0;
  private static final int DOOR_TYPE_POS = 1;
  private static final int TRANSMISSION_TYPE_POS = 2;
  private static final int FUEL_AC_TYPE_POS = 3;
  
  // Holds all the data about SIPP specifications.
  private SippSpecs sippSpecsTable;
  
  public SippSpecsService() {
    sippJsonToJava();
  }
  
  /**
   * @return a SippSpecs container with all the SIPP data.
   */
  public SippSpecs getSippSpecs() {
    return this.sippSpecsTable;
  }
  
  /**
   * @param v is the vehicle.
   * @return the type of a vehicle.
   */
  public String getCarType(Vehicle v) {
    Character sippLetter = v.getSipp().charAt(CAR_TYPE_POS);
    
    // If the SIPP letter is recognized return its value.
    if(sippSpecsTable.getCarTypesMap().containsKey(sippLetter)){
      return sippSpecsTable.getCarTypesMap().get(sippLetter).getValue();
    }
    
    return "unknown";
  }
  
  /**
   * @param v is the vehicle.
   * @return the doors type of a vehicle.
   */
  public String getDoorsType(Vehicle v) {
    Character sippLetter = v.getSipp().charAt(DOOR_TYPE_POS);
    
    // If the SIPP letter is recognized return its value.
    if(sippSpecsTable.getDoorTypesMap().containsKey(sippLetter))
      return sippSpecsTable.getDoorTypesMap().get(sippLetter).getValue();
    
     return "unknown";
   }
  
  /**
   * @param v is the vehicle.
   * @return the transmission type of a vehicle.
   */
  public String getTransmissionType(Vehicle v) {
    Character sippLetter = v.getSipp().charAt(TRANSMISSION_TYPE_POS);
    
    // If the SIPP letter is recognized return its value.
    if(sippSpecsTable.getTransmissionTypesMap().containsKey(sippLetter))
      return sippSpecsTable.getTransmissionTypesMap().get(sippLetter).getValue();
    
    return "unknown";
  }
  
  /**
   * @param v is the vehicle.
   * @return the fuel type of a vehicle.
   */
  public String getFuelType(Vehicle v) {
    Character sippLetter = v.getSipp().charAt(FUEL_AC_TYPE_POS);
    
    // If the SIPP letter is recognized return its value.
    if(sippSpecsTable.getFuelAcMap().containsKey(sippLetter))
      return sippSpecsTable.getFuelAcMap().get(sippLetter).getValue().split("/")[0];
  
    return "unknown";
  }
  
  /**
   * @param v is the vehicle.
   * @return the air conditioning type of a vehicle.
   */
  public String getAcType(Vehicle v) {
    Character sippLetter = v.getSipp().charAt(FUEL_AC_TYPE_POS);
    
    // If the SIPP letter is recognized return its value.
    if(sippSpecsTable.getFuelAcMap().containsKey(sippLetter))
      return sippSpecsTable.getFuelAcMap().get(sippLetter).getValue().split("/")[1];
  
    return "unknown";
  }
   
  /**
   * Calculates the SIPP score of a vehicle.
   * 
   * @param v is the vehicle.
   * @return the SIPP score of a vehicle.
   */
  public int getSippScore(Vehicle v) {
    int score = 0;
    Character sippLetter;
    
    sippLetter = v.getSipp().charAt(TRANSMISSION_TYPE_POS);
    // If the SIPP letter is recognized add up its corresponding score.
    if(sippSpecsTable.getTransmissionTypesMap().containsKey(sippLetter))
     score += sippSpecsTable.getTransmissionTypesMap().get(sippLetter).getScore();

    sippLetter = v.getSipp().charAt(FUEL_AC_TYPE_POS); 
    // If the SIPP letter is recognized add up its corresponding score.
    if(sippSpecsTable.getFuelAcMap().containsKey(sippLetter))
     score += sippSpecsTable.getFuelAcMap().get(sippLetter).getScore();

    return score;
  }
  
  /**
   * Gets the SIPP from corresponding JSON files and deserializes it.
   * Each letter is mapped to its corresponding specifications (value and score
   * wrapped in a LetterSpecs class).
   */
  public void sippJsonToJava() {
    ObjectMapper mapper = new ObjectMapper();  
    sippSpecsTable = new SippSpecs();
    
    try {
      ClassLoader classloader = getClass().getClassLoader();
      
      sippSpecsTable.setCarTypesMap(
        mapper.readValue(classloader.getResourceAsStream(CAR_TYPES_PATH),
            new TypeReference<Map<Character, LetterSpecsWrapper>>(){}));
      
      sippSpecsTable.setDoorTypesMap(
        mapper.readValue(classloader.getResourceAsStream(DOOR_TYPES_PATH),
            new TypeReference<Map<Character, LetterSpecsWrapper>>(){}));
      
      sippSpecsTable.setTransmissionTypesMap(
        mapper.readValue(classloader.getResourceAsStream(TRANSMISSION_TYPES_PATH),
            new TypeReference<Map<Character, LetterSpecsWrapper>>(){}));
      
      sippSpecsTable.setFuelAcMap(
        mapper.readValue(classloader.getResourceAsStream(FUEL_AC_TYPES_PATH),
            new TypeReference<Map<Character, LetterSpecsWrapper>>(){}));   
    } 
    catch (IOException e) { e.printStackTrace(); } 
  }
  
}
