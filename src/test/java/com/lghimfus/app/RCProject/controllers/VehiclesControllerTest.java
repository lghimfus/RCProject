package com.lghimfus.app.RCProject.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.services.VehicleService;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import spark.Spark;

public class VehiclesControllerTest{
  
  private final static String LOCALHOST = "http://localhost:4567";
  private static OkHttpClient httpclient;

  @BeforeClass
  public static void beforeClass() {
    // Create new http client to deal with requests.
    httpclient = new OkHttpClient();
    
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
    textTest("/vehiclesByPrice");
    jsonTest("/vehiclesByPrice","full");
    jsonTest("/vehiclesByPrice","custom");
    jsonTest("/vehiclesByPrice","inexistent_value");
  }
  
  @Test
  public void vehiclesBySpecsTest() {
    textTest("/vehiclesBySpecs");
    jsonTest("/vehiclesBySpecs","full");
    jsonTest("/vehiclesBySpecs","custom");
    jsonTest("/vehiclesBySpecs","inexistent_value");
  }
  
  @Test
  public void vehiclesBySupplierRatingTest() {
    textTest("/vehiclesBySupplierRating");
    jsonTest("/vehiclesBySupplierRating","full");
    jsonTest("/vehiclesBySupplierRating","custom");
    jsonTest("/vehiclesBySupplierRating","inexistent_value");
  }
  
  @Test
  public void vehiclesByScoreTest() {
    textTest("/vehiclesByScore");
    jsonTest("/vehiclesByScore","full");
    jsonTest("/vehiclesByScore","custom");
    jsonTest("/vehiclesByScore","inexistent_value");
  }
  
  @Test
  public void vehicleListTest() {
    jsonTest("/vehicles", null);
  }
  
  /**
   * Tests for the correct response code, content type and body content of an
   * HTTP response.
   * Used for application/json responses.
   * 
   * @param path the path under test.
   * @param value value of the parameter under test.
   */
  public void jsonTest(String path, String value) {
    HttpUrl.Builder httpBuider = HttpUrl.parse(LOCALHOST + path).newBuilder();
    httpBuider.addQueryParameter("json", value);
    
    Request request = new Request.Builder().url(httpBuider.build()).build();
    
    httpclient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            fail("Couldn't connect to" + httpBuider.build());
        }
    
        @Override
        public void onResponse(Call call, Response response) throws IOException {
          assertEquals(200, response.code());
          assertEquals("application/json", response.networkResponse());
          
          JSONArray jsonVehicleList = new JSONArray(response.body().string());
          assertEquals(3, jsonVehicleList.length());
        }
    });
  }
  
  /**
   * Tests for the correct response code, content type and body content of an
   * HTTP response.
   * Used for text/plain responses.
   * 
   * @param path the path under test.
   */
  public void textTest(String path) {
    HttpUrl.Builder httpBuider = HttpUrl.parse(LOCALHOST + path).newBuilder();
    Request request = new Request.Builder().url(httpBuider.build()).build();
    httpclient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            fail("Couldn't connect to" + httpBuider.build());
        }
    
        @Override
        public void onResponse(Call call, Response response) throws IOException {
          assertEquals(200, response.code());
          assertEquals("text/plain", response.networkResponse());

          assertTrue(!response.body().string().equals(null));
        }
    });
  }

}
