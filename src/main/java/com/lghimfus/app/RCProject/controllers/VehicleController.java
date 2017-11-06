package com.lghimfus.app.RCProject.controllers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
  
  public VehicleController(final VehicleService vehicleService) {

    Spark.get("/vehiclesByPrice", (req, res) ->  {
      res.status(200);
      res.type("text/plain");
      return FormatUtils.formatAllByPrice(vehicleService.findAllOrderByPriceAsc());
    });
      
    Spark.get("/vehiclesBySpecs", (req, res) ->  {
      res.status(200);
      res.type("text/plain");
      return FormatUtils.formatAllBySpecs(vehicleService.findAll());
    });
    
    Spark.get("/vehiclesBySupplierRating", (req, res) -> {
      res.status(200);
      res.type("text/plain");
      return FormatUtils.formatAllBySupplier(
          vehicleService.findAllTypesOrderByHighestRatedSupplierDesc());
    });
    
    Spark.get("/vehiclesByScore", (req, res) -> {
      res.status(200);
      res.type("text/plain");
      return FormatUtils.formatAllByScore(
          vehicleService.findAllOrderByCombinedScoreDesc());
    });
        
    Spark.get("/vehiclesByPriceJson", (req, res) -> {
      res.status(200);
      res.type("application/json");
      return javaToJson(vehicleService.findAllOrderByPriceAsc(), 
          new VehicleByPriceSerializer());
    });
    
    Spark.get("/vehiclesBySpecsJson", (req, res) -> {
      res.status(200);
      res.type("application/json");
      return javaToJson(vehicleService.findAll(), new VehicleBySpecsSerializer());
    });
    
    Spark.get("/vehiclesBySupplierRatingJson", (req, res) -> {
      res.status(200);
      res.type("application/json");
      return javaToJson(vehicleService.findAllTypesOrderByHighestRatedSupplierDesc(),
          new VehicleBySupplierRatingSerializer());
    });
    
    Spark.get("/vehiclesByScoreJson", (req, res) -> {
      res.status(200);
      res.type("application/json");
      return javaToJson(vehicleService.findAllOrderByCombinedScoreDesc(), 
          new VehicleByScoreSerializer());
    });
    
    Spark.get("/vehicles", (req, res) -> {
      res.status(200);
      res.type("application/json");
      
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(vehicleService.findAll());
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
      module.addSerializer(Vehicle.class, serializer);
      mapper.registerModule(module);
      
      return mapper.writeValueAsString(vehicles);
    } 
    catch (IOException e) {
      throw new RuntimeException("IOException");
    }
  }
  
}
