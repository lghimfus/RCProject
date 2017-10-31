package com.lghimfus.app.RCProject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lghimfus.app.RCProject.models.Vehicle;
import com.lghimfus.app.RCProject.utils.NetworkUtils;

/**
 * Console application.
 *
 */
public class App {
	
	private final static String URL_PATH = "http://www.rentalcars.com/js/vehicles.json";
	
    public static void main( String[] args ) {
   
        List<Vehicle> vehicleList = getVehicleList();

//        for (Vehicle vehicle : vehicleList) {
//        	System.out.println(vehicle);
//        }
        
    }
    
	/**
	 * This method gets the JSON string from the HTTP response and returns a list of Java Vehicle objects.
     *
     * @return List of Java Vehicle objects from the HTTP JSON response.
     */
    public static List<Vehicle> getVehicleList() {
    	
        ObjectMapper objectMapper = new ObjectMapper();
        List<Vehicle> vehicleList = null;
        
		try {		
			// Get the JSON response from the given URL.
			String resultJSONString = NetworkUtils.getResponseFromHttpUrl(new URL(URL_PATH));
        	
        	// Convert resulted JSON string to a JSON object.
            JSONObject resultJsonObject = new JSONObject(resultJSONString);
            
            // Get VehicleList array of JSON objects.
            JSONArray jsonVehicleList = resultJsonObject.getJSONObject("Search").getJSONArray("VehicleList");  
	        
            // Map entries of the VehicleList JSON array to Java Vehicle objects.
			vehicleList = Arrays.asList(objectMapper.readValue(jsonVehicleList.toString(), Vehicle[].class));
		} 
		catch (JsonParseException e) { e.printStackTrace(); }
		catch (JsonMappingException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }

        return vehicleList;
    }
    
}
        


