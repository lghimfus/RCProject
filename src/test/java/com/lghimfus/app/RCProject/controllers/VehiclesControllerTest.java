package com.lghimfus.app.RCProject.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.services.VehicleService;

import spark.Spark;
import spark.utils.IOUtils;

public class VehiclesControllerTest{
  
  @BeforeClass
  public static void beforeClass() {
    // List of vehicles to add in the world for testing purposes.
    List<Vehicle> vehicles = new ArrayList<>();
    
    vehicles.add(new Vehicle("CWMR", "Vehicle1", 1.3, "Hertz", 1.1));
    vehicles.add(new Vehicle("CWMR", "Vehicle2", 1.2, "Hertz", 1.2));
    vehicles.add(new Vehicle("CWMR", "Vehicle3", 1.1, "Hertz", 1.3)); 
    
    // VehicleService used to instantiate the controller.
    VehicleService vehicleService = new VehicleService(vehicles);
    
    // Create a new controller which is equivalent to starting the REST API.
    new VehicleController(vehicleService);
    
    // Wait until Spark is set up.
    Spark.awaitInitialization();
  }

  @AfterClass
  public static void afterClass() {
    Spark.stop();
  }
  
  @Test
  public void vehiclesByPriceTest() {
    outputTest("/vehiclesByPrice");
  }
  
  @Test
  public void vehiclesBySpecsTest() {
    outputTest("/vehiclesBySpecs");
  }
  
  @Test
  public void vehiclesBySupplierRatingTest() {
    outputTest("/vehiclesBySupplierRating");
  }
  
  @Test
  public void vehiclesByScoreTest() {
    outputTest("/vehiclesByScore");
  }
  
  @Test
  public void vehiclesByPriceJsonTest() {
    jsonTest("/vehiclesByPriceJson");
  }
  
  @Test
  public void vehiclesBySpecsJsonTest() {
    jsonTest("/vehiclesBySpecsJson");
  }
  
  @Test
  public void vehiclesBySupplierRatingJsonTest() {
    TestResponse response = request("GET", "/vehiclesBySupplierRatingJson");
    
    assertEquals(200, response.status);
    assertEquals("application/json", response.contentType);
    
    JSONArray jsonVehicleList = new JSONArray(response.body);
    assertEquals(1, jsonVehicleList.length());
  }
  
  @Test
  public void vehiclesByScoreJsonTest() {
    jsonTest("/vehiclesByScoreJson");
  }
  
  @Test
  public void vehicleListTest() {
    jsonTest("/vehicles");
  }
  
  /**
   * Tests for the correct response code, content type and body content of an
   * HTTP response.
   * Used for application//json responses.
   * 
   * @param path the path under test.
   */
  public void jsonTest(String path) {
    TestResponse response = request("GET", path);
    
    assertEquals(200, response.status);
    assertEquals("application/json", response.contentType);
    
    JSONArray jsonVehicleList = new JSONArray(response.body);
    assertEquals(3, jsonVehicleList.length());
  }
  
  /**
   * Tests for the correct response code, content type and body content of an
   * HTTP response.
   * Used for text/plain responses.
   * 
   * @param path the path under test.
   */
  public void outputTest(String path) {
    TestResponse response = request("GET", path);
    
    assertEquals(200, response.status);
    assertEquals("text/plain", response.contentType);

    assertTrue(!response.body.equals(null));
  }
  
  /**
   * Creates a connection to a certain path and grabs the content type, the 
   * response code and the body of the response.
   * 
   * @param method the http method.
   * @param path path under test.
   * @return new wrapper object with the content type, the body and status of 
   *         the response.
   */
  private TestResponse request(String method, String path) {
    try {
      URL url = new URL("http://localhost:4567" + path);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(method);
      connection.setDoOutput(true);
      connection.connect();
      
      return new TestResponse(
          connection.getResponseCode(), 
          connection.getContentType(),
          IOUtils.toString(connection.getInputStream()) );
    } 
    catch (IOException e) {
      fail("Sending request failed: " + e.getMessage());
      return null;
    }
  }
  
  /**
   * Wrapper class for the content type, the body and status of an HTTP response.
   * 
   * @author lghimfus
   *
   */
  private static class TestResponse {
    public final String body;
    public final String contentType;
    public final int status;

    public TestResponse(int status, String contentType, String body) {
      this.status = status;
      this.contentType = contentType;
      this.body = body;
    }
  }

}
