package com.lghimfus.app.RCProject.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lghimfus.app.RCProject.models.Vehicle;

public class VehicleServiceTest {
  
  private VehicleService vehicleService;
  
//List of vehicles to add in the world for testing purposes.  
  List<Vehicle> vehicles;
  
  @Before
  public void setup() {
    
    vehicles = new ArrayList<>();  
    vehicles.add(new Vehicle("CWMR", "Vehicle1", 1.3, "Hertz", 1.1));
    vehicles.add(new Vehicle("CWMR", "Vehicle2", 1.2, "Hertz", 1.2));
    vehicles.add(new Vehicle("CWMR", "Vehicle3", 1.1, "Hertz", 1.3));  

    vehicleService = new VehicleService(vehicles);
  }
  
  @Test
  public void testFindAll() {
    assertEquals("findAll should find all vehicles", 3, vehicles.size());
  }
  
  @Test
  public void testFindAllOrderByPriceAsc() {
    List<Vehicle> sortedVehicleList = vehicleService.findAllOrderByPriceAsc();
    
    assertEquals("Vehicle3", sortedVehicleList.get(0).getName());
    assertEquals("Vehicle2", sortedVehicleList.get(1).getName());
    assertEquals("Vehicle1", sortedVehicleList.get(2).getName());
  }
  
  /**
   * Tests if vehicles are sorted in descending order by combined score.
   */
  @Test
  public void testFindAllOrderByCombinedScoreDesc() {
    List<Vehicle> sortedVehicleList = vehicleService.findAllOrderByCombinedScoreDesc();
    
    assertEquals("Vehicle3", sortedVehicleList.get(0).getName());
    assertEquals("Vehicle2", sortedVehicleList.get(1).getName());
    assertEquals("Vehicle1", sortedVehicleList.get(2).getName());
  }
  
  @Test
  public void testFindAllByCarType() {
    List<Vehicle> sortedVehicleList = vehicleService.findAllTypesOrderByHighestRatedSupplierDesc();
    
    assertEquals("Vehicle3", sortedVehicleList.get(0).getName()); 
  }
  
  @Test
  public void testVehiclesJsonToJava() {
    VehicleService localVehicleService = new VehicleService();
    localVehicleService.vehiclesJsonToJava("http://www.rentalcars.com/js/vehicles.json");
    
    assertNotNull(localVehicleService.findAll());
    assertEquals("findAll should find all vehicles", 31, localVehicleService.findAll().size());
  }
  
}
