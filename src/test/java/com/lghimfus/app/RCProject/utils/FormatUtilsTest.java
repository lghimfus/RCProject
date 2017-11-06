package com.lghimfus.app.RCProject.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.services.VehicleService;

public class FormatUtilsTest {
  // List of vehicles to add in the world for testing purposes.
  List<Vehicle> vehicles;
  
  @Before
  public void setup() {
    vehicles = new ArrayList<>();   
    vehicles.add(new Vehicle("CWMR", "Vehicle1", 1.3, "Hertz", 1.1));
    
    VehicleService.resolveSipp(vehicles);
    VehicleService.resolveScores(vehicles);
  }
  
  @Test
  public void testFormatAllByPrice() {
    String expected = "{Vehicle1} - {1.30}";
    String actual = FormatUtils.formatAllByPrice(vehicles);
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testFormatAllBySpecs() {
    String expected = "{Vehicle1} - {CWMR} - {Compact} - {Estate} - {Manual} - {Petrol} - {AC}";
    String actual = FormatUtils.formatAllBySpecs(vehicles);
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testFormatAllBySupplier() {
    String expected = "{Vehicle1} - {Compact} - {Hertz} - {1.10}";
    String actual = FormatUtils.formatAllBySupplier(vehicles);
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testformatAllByScore() {
    String expected = "{Vehicle1} - {3} - {1.10} - {4.10}";
    String actual = FormatUtils.formatAllByScore((vehicles));
    
    assertEquals(expected, actual);
  }

}
