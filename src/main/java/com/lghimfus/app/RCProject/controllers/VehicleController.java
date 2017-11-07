package com.lghimfus.app.RCProject.controllers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.serializers.VehicleByPriceSerializer;
import com.lghimfus.app.RCProject.serializers.VehicleByScoreSerializer;
import com.lghimfus.app.RCProject.serializers.VehicleBySpecsSerializer;
import com.lghimfus.app.RCProject.serializers.VehicleBySupplierRatingSerializer;
import com.lghimfus.app.RCProject.services.VehicleService;
import com.lghimfus.app.RCProject.utils.FormatUtils;

import spark.Spark;

/**
 * Rest application
 * 
 * @author lghimfus
 *
 */
public class VehicleController {
  
  private final String CUSTOM_JSON = "custom";
  private final String FULL_JSON = "full";
  
  public VehicleController(final VehicleService vehicleService) {
    
    Spark.get("/vehicles", (req, res) -> {
      res.status(200);
      res.type("application/json");
      return javaToJson(vehicleService.findAll(), null);
    });
        
    Spark.get("/vehiclesByPrice", (req, res) -> {
      res.status(200);
      
      if (req.queryParams().contains("json")) {
        res.type("application/json");
      
        switch(req.queryParams("json")) {
          case CUSTOM_JSON:
            return javaToJson(vehicleService.findAllOrderByPriceAsc(), new VehicleByPriceSerializer());
          case FULL_JSON:
          default:
            return javaToJson(vehicleService.findAllOrderByPriceAsc(), null);
        }
      }
      else {
        res.type("text/plain");
        return FormatUtils.formatAllByPrice(vehicleService.findAllOrderByPriceAsc());
      }
    });
    
    Spark.get("/vehiclesBySpecs", (req, res) -> {
      res.status(200);
      
      if (req.queryParams().contains("json")) {
        res.type("application/json");
        
        switch(req.queryParams("json")) {
          case CUSTOM_JSON:
            return javaToJson(vehicleService.findAll(), new VehicleBySpecsSerializer());
          case FULL_JSON:
          default:
            return javaToJson(vehicleService.findAll(), null);
        }
      }
      else {
        res.type("text/plain");
        return FormatUtils.formatAllBySpecs(vehicleService.findAll());
      }
    });
    
    Spark.get("/vehiclesBySupplierRating", (req, res) -> {
      res.status(200);
      
      if (req.queryParams().contains("json")) {
        res.type("application/json");
        
        switch(req.queryParams("json")) {
          case CUSTOM_JSON:
            return javaToJson(vehicleService.findAllTypesOrderByHighestRatedSupplierDesc(),
                new VehicleBySupplierRatingSerializer());
          case FULL_JSON:
          default:
            return javaToJson(vehicleService.findAllTypesOrderByHighestRatedSupplierDesc(), null);
          }
      }
      else {
        res.type("text/plain");
        return FormatUtils.formatAllBySupplier(vehicleService.findAllTypesOrderByHighestRatedSupplierDesc());
      }
    });
    
    Spark.get("/vehiclesByScore", (req, res) -> {
      res.status(200);
      
      if (req.queryParams().contains("json")) {
        res.type("application/json");
        
        switch(req.queryParams("json")) {
          case CUSTOM_JSON:
            return javaToJson(vehicleService.findAllOrderByCombinedScoreDesc(), 
                new VehicleByScoreSerializer());
          case FULL_JSON:
          default:
            return javaToJson(vehicleService.findAllOrderByCombinedScoreDesc(), null);
          }
      }
      else {
        res.type("text/plain");
        return FormatUtils.formatAllByScore(vehicleService.findAllOrderByCombinedScoreDesc());
      }
    });
  
  } // main
  
  /**
   * Helps to serialize a list of Vehicle objects.
   * 
   * @param vehicles the list of vehicles.
   * @param serializer the custom serializer under test.
   * @return a string from the list Vehicle objects.
   */
  public static String javaToJson(List<Vehicle> vehicles, JsonSerializer<Vehicle> serializer) {
    try {
      ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
      SimpleModule module = new SimpleModule();
      if (serializer != null)
        module.addSerializer(Vehicle.class, serializer);
      mapper.registerModule(module);
      
      return mapper.writeValueAsString(vehicles);
    } 
    catch (IOException e) {
      throw new RuntimeException("IOException");
    }
  }
  
}
