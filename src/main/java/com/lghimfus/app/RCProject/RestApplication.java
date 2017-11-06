package com.lghimfus.app.RCProject;

import com.lghimfus.app.RCProject.controllers.VehicleController;
import com.lghimfus.app.RCProject.services.VehicleService;

/**
 * Rest application
 * 
 * @author lghimfus
 *
 */
public class RestApplication {
  
  public static void main(String[] args) {
    new VehicleController(
        new VehicleService("http://www.rentalcars.com/js/vehicles.json"));
  }

}
