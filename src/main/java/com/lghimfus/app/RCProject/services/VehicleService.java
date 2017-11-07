package com.lghimfus.app.RCProject.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.models.VehicleSpecs;
import com.lghimfus.app.RCProject.utils.NetworkUtils;

/**
 * This class consists of methods that handle requests from a client.
 * 
 * @author lghimfus
 *
 */
public class VehicleService {
  
  private List<Vehicle> vehicleList;
  
  // Helps with the logic related to vehicle's SIPP.
  static SippSpecsService sippSpecsService;
  
  public VehicleService() {  
  }
  
  /**
   * This constructor was created to make testing easier.
   * 
   * @param vehicleList a list of Vehicle objects.
   */
  public VehicleService(List<Vehicle> vehicleList) {
    this.vehicleList = vehicleList;
    resolveSipp(this.vehicleList);
  }
  
  /**
   * This is the main constructor used to handle the required test tasks.
   * 
   * @param vehiclesUrlPath is the Rentalcars.com URL where the vehicles json 
   *        is found.
   */
  public VehicleService(String vehiclesUrlPath) {
    vehiclesJsonToJava(vehiclesUrlPath); 
    resolveSipp(this.vehicleList);
  }
  
  /**
   * @return a list of all the vehicles.
   */
  public List<Vehicle> findAll() {
    return vehicleList;
  }
  
  /**
   * Gets the list of all the vehicles and sorts it by vehicles price.
   * 
   * @return a list of all the vehicles sorted in ascending price order.
   */
  public List<Vehicle> findAllOrderByPriceAsc() {
    return this.findAll().stream()
        .sorted(Comparator.comparingDouble(Vehicle::getPrice))
        .collect(Collectors.toList());
  }
  
  /**
   * Gets the list of all the vehicles and sorts it by vehicles combined_score.
   * 
   * The combined_score is the vehicle score plus the supplier rating.
   * 
   * @return a list of all the vehicles sorted in descending combined_score order.
   */
  public List<Vehicle> findAllOrderByCombinedScoreDesc(){
    // Resolves SIPP scores.
    resolveScores(this.findAll());
    
    return 
        this.findAll().stream()
          .sorted((v1, v2) -> {
            double v1Score = v1.getRating() + v1.getVehicleSpecs().getScore();
            double v2Score = v2.getRating() + v2.getVehicleSpecs().getScore();
            return Double.compare(v2Score, v1Score);
          })
          .collect(Collectors.toList());
  }
  
  /**
   * Gets the list of all the vehicles and finds those that belongs to a certain
   * car type.
   * 
   * @param type is the car type.
   * 
   * @return a list of all the vehicles belonging to a certain car type.
   */
  public List<Vehicle> findAllByCarType(String type) {
      return this.findAll().stream()
          .filter(vehicle -> vehicle.getVehicleSpecs().getCarType().equals(type))
          .collect(Collectors.toList());
  }
  
  /**
   * Gets the list of all the vehicles, groups them by type and finds the 
   * highest rated supplier of each group.
   * 
   * @return a list the vehicles with the highest rated supplier.
   */
  public List<Vehicle> findAllTypesOrderByHighestRatedSupplierDesc() {
    List<Vehicle> highestRatedList = new ArrayList<Vehicle>();
    
    /*
     *  For each car type, finds all the vehicles and gets the vehicle with the
     *  highest rating.
     */   
    sippSpecsService.getSippSpecs().getCarTypesMap()
        .forEach((letter, letterSpecs) -> {
            this.findAllByCarType(letterSpecs.getValue()).stream()
              .max(Comparator.comparing(Vehicle::getRating))
              .ifPresent(max -> highestRatedList.add(max));
        });
    
    
    // Returns a vehicle list in descending order.
    return highestRatedList.stream()
        .sorted(Comparator.comparingDouble(Vehicle::getRating).reversed())
        .collect(Collectors.toList() );
  }
  
  
  /**
   * Resolves SIPP score for each vehicle.
   */
  public static void resolveScores(List<Vehicle> vehicleList) {
    vehicleList.forEach(vehicle -> {
      vehicle.getVehicleSpecs().setScore(sippSpecsService.getSippScore(vehicle));
    });
  }
  
  /**
   * For each SIPP letter, gets its value and sets the specifications for each
   * vehicle.
   */
  public static void resolveSipp(List<Vehicle> vehicleList) {
    sippSpecsService = new SippSpecsService();
    
    vehicleList.forEach(vehicle -> {
      VehicleSpecs vehicleSpecs = new VehicleSpecs(
          sippSpecsService.getCarType(vehicle),
          sippSpecsService.getDoorsType(vehicle),
          sippSpecsService.getTransmissionType(vehicle),
          sippSpecsService.getFuelType(vehicle),
          sippSpecsService.getAcType(vehicle)
      );

      vehicle.setVehicleSpecs(vehicleSpecs);
    });
  }
  
  /**
   * Gets the JSON response from the HTTP URL and deserializes it to 
   * Vehicle java objects.
   */
  public void vehiclesJsonToJava(String vehiclesUrlPath) {
    ObjectMapper mapper = new ObjectMapper();
        
    try {
      // Gets the JSON response from the HTTP URL. 
      JSONObject jsonResult = 
              NetworkUtils.getJsonResponseFromHttpUrl(new URL(vehiclesUrlPath));
      
      // Gets the VehicleList node from the JSON response.
      JSONArray jsonVehicleList = 
          jsonResult.getJSONObject("Search").getJSONArray("VehicleList");  
    
      // Maps entries of the VehicleList JSON array to Java Vehicle objects.
      vehicleList = Arrays.asList(
              mapper.readValue(jsonVehicleList.toString(), Vehicle[].class));      
    }
    catch (IOException e) { e.printStackTrace(); } 
  }

}
