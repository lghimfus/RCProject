package com.lghimfus.app.RCProject.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.models.SippSpecs;
import com.lghimfus.app.RCProject.models.SippSpecs.LetterSpecsWrapper;

public class SippSpecsServiceTest {
  
  // Helps with logic related to SIPP code.
  SippSpecsService sippSpecsService;
  
  // List of vehicles to add in the world for testing purposes.  
  List<Vehicle> vehicles;

  @Before
  public void setup() {
    
    vehicles = new ArrayList<>();  
    
    /*
     * carType: Compact
     * doorType: Estate
     * transmission: Manual (1 score point)
     * fuel: Petrol
     * airCon: AC (2 score points)
     * score: 3
     */
    vehicles.add(new Vehicle("CWMR", "Vehicle1", 1.3, "Hertz", 1.1));
    
    /*
     * carType: Luxury
     * doorType: 4 doors
     * transmission: Automatic (5 score points)
     * fuel: Petrol
     * airCon: no AC
     * score: 5
     */
    vehicles.add(new Vehicle("LCAN", "Vehicle2", 1.2, "Hertz", 1.2));
    
    /*
     * carType: unknown
     * doorType: unknown
     * transmission: unknown
     * fuel: unknown
     * airCon: unknown
     * score: 0
     */
    vehicles.add(new Vehicle("ZZZZ", "Vehicle3", 1.3, "Hertz", 1.3));
    
    VehicleService.resolveSipp(vehicles);
    sippSpecsService = new SippSpecsService();
  }
  
  @Test
  public void testGetCarType() {
    assertEquals("Compact", sippSpecsService.getCarType(vehicles.get(0)));
    assertEquals("Luxury", sippSpecsService.getCarType(vehicles.get(1)));
    assertEquals("unknown", sippSpecsService.getCarType(vehicles.get(2)));
  }
  
  @Test
  public void testGetDoorsType() {
    assertEquals("Estate", sippSpecsService.getDoorsType(vehicles.get(0)));
    assertEquals("4 doors", sippSpecsService.getDoorsType(vehicles.get(1)));
    assertEquals("unknown", sippSpecsService.getDoorsType(vehicles.get(2)));
  }
  
  @Test
  public void testGetTransmissionType() {
    assertEquals("Manual", sippSpecsService.getTransmissionType(vehicles.get(0)));
    assertEquals("Automatic", sippSpecsService.getTransmissionType(vehicles.get(1)));
    assertEquals("unknown", sippSpecsService.getTransmissionType(vehicles.get(2)));
  }
  
  @Test
  public void testGetFuelType() {
    assertEquals("Petrol", sippSpecsService.getFuelType(vehicles.get(0)));
    assertEquals("unknown", sippSpecsService.getFuelType(vehicles.get(2)));
  }
  
  @Test
  public void testGetAcType() {
    assertEquals("AC", sippSpecsService.getAcType(vehicles.get(0)));
    assertEquals("no AC", sippSpecsService.getAcType(vehicles.get(1)));
    assertEquals("unknown", sippSpecsService.getAcType(vehicles.get(2)));
  }
  
  @Test
  public void testGetSippScore() {
    assertEquals(3, sippSpecsService.getSippScore(vehicles.get(0)));
    assertEquals(5, sippSpecsService.getSippScore(vehicles.get(1)));
    assertEquals(0, sippSpecsService.getSippScore(vehicles.get(2)));
  }
  
  @Test
  public void sippJsonToJava() {
    sippSpecsService.sippJsonToJava();
    assertNotNull(sippSpecsService.getSippSpecs());
    
   SippSpecs sippSpecs = sippSpecsService.getSippSpecs();
   
   Map<Character, LetterSpecsWrapper> transmissionTypesMap = sippSpecs.getTransmissionTypesMap(); 
   
   assertEquals("Manual", transmissionTypesMap.get("M".charAt(0)).getValue());
   assertEquals("Automatic", transmissionTypesMap.get("A".charAt(0)).getValue());
   
   assertEquals(1, transmissionTypesMap.get("M".charAt(0)).getScore());
   assertEquals(5, transmissionTypesMap.get("A".charAt(0)).getScore());
  }
  
}
