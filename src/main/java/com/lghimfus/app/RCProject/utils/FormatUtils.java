package com.lghimfus.app.RCProject.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.lghimfus.app.RCProject.models.Vehicle;

/**
 * This class helps with the required print formatting.
 * 
 * @author lghimfus
 *
 */
public class FormatUtils {
  
  /**
   * Handles the required print format for part 1, task 1.
   */
  public static String formatAllByPrice(List<Vehicle> vehicleList) {
    return vehicleList.stream()
      .map(vehicle -> String.format("{%s} - {%.2f}", 
        vehicle.getName(), 
        vehicle.getPrice() ))
          .collect(Collectors.joining("\n"));
  }
  
  /**
   * Handles the required print format for part 1, task 2.
   */
  public static String formatAllBySpecs(List<Vehicle> vehicleList) {
    return vehicleList.stream()
      .map(vehicle -> String.format("{%s} - {%s} - {%s} - {%s} - {%s} - {%s} - {%s}",
            vehicle.getName(),
            vehicle.getSipp(),
            vehicle.getVehicleSpecs().getCarType(),
            vehicle.getVehicleSpecs().getDoorsType(),
            vehicle.getVehicleSpecs().getTransmission(),
            vehicle.getVehicleSpecs().getFuel(),
            vehicle.getVehicleSpecs().getAirCon() ))
      .collect(Collectors.joining("\n"));
  }
  
  /**
   * Handles the required print format for part 1, task 3.
   */
  public static String formatAllBySupplier(List<Vehicle> vehicleList) {
    return vehicleList.stream()
      .map(vehicle -> String.format("{%s} - {%s} - {%s} - {%.2f}",
            vehicle.getName(),
            vehicle.getVehicleSpecs().getCarType(),
            vehicle.getSupplier(),
            vehicle.getRating()))
        .collect(Collectors.joining("\n"));
  }

  /**
   * Handles the required print format for part 1, task 4.
   */
  public static String formatAllByScore(List<Vehicle> vehicleList) {
    return vehicleList.stream()
      .map(vehicle -> 
            String.format("{%s} - {%d} - {%.2f} - {%.2f}",
            vehicle.getName(),
            vehicle.getVehicleSpecs().getScore(),
            vehicle.getRating(),
            vehicle.getRating() + vehicle.getVehicleSpecs().getScore())
      )
      .collect(Collectors.joining("\n"));
  }
  
}
