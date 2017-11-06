package com.lghimfus.app.RCProject.serializers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.services.VehicleService;

import spark.utils.IOUtils;

public class JacksonSerializerTest {
  
  // List of vehicles to add in the world for testing purposes.  
  List<Vehicle> vehicles;
  
  // Helps to find correct path when reading from files.
  ClassLoader classLoader;
  
  @Before
  public void setup() {
    classLoader = getClass().getClassLoader();
    
    vehicles = new ArrayList<>();  
    vehicles.add(new Vehicle("CWMR", "Vehicle1", 1.3, "Hertz", 1.1));
    vehicles.add(new Vehicle("CWMR", "Vehicle2", 1.2, "Hertz", 1.2));
    vehicles.add(new Vehicle("CWMR", "Vehicle3", 1.1, "Hertz", 1.3));
    
    VehicleService.resolveSipp(vehicles);
  }
  
  @Test
  public void testVehicleByPriceSerialization() throws Exception {
    String result = javaToJson(vehicles, new VehicleByPriceSerializer());
    String expected = readFromFile("vehiclesByPrice.json");
    assertEquals(expected, result);
  }
  
  @Test
  public void testVehicleByScoreSerialization() throws Exception {
    String result = javaToJson(vehicles, new VehicleByScoreSerializer());
    String expected = readFromFile("vehiclesByScore.json");
    assertEquals(expected, result);
  }
  
  @Test
  public void testVehicleBySpecsSerialization() throws Exception {
    String result = javaToJson(vehicles, new VehicleBySpecsSerializer());
    String expected = readFromFile("vehiclesBySpecs.json");
    assertEquals(expected, result);
  }
  
  @Test
  public void testVehicleBySupplierRatingSerialization() throws Exception {
    String result = javaToJson(vehicles, new VehicleBySupplierRatingSerializer());
    String expected = readFromFile("vehiclesBySupplierRating.json");
    assertEquals(expected, result);
  } 
  
  /**
   * Helps to read a string from a json file.
   * 
   * @param fileName the name of the file containing the json.
   * @return a string from the json file.
   */
  public String readFromFile (String fileName) {
    InputStream inputStream = classLoader.getResourceAsStream(fileName);
    try {
      return IOUtils.toString(inputStream);
    } 
    catch (IOException e) { 
      throw new RuntimeException("IOException");
    }
  }
  
  /**
   * Helps to serialize a list of Vehicle objects.
   * 
   * @param vehicles the list of vehicles.
   * @param serializer the custom serializer under test.
   * @return a string from the list Vehicle objects.
   */
  public String javaToJson(List<Vehicle> vehicles, JsonSerializer<Vehicle> serializer) {
    try {
      ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
      SimpleModule module = new SimpleModule();
      module.addSerializer(Vehicle.class, serializer);
      mapper.registerModule(module);
      
      return mapper.writeValueAsString(vehicles);
    } 
    catch (IOException e) {
      throw new RuntimeException("IOException");
    }
  }
    
}