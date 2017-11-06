package com.lghimfus.app.RCProject;

import com.lghimfus.app.RCProject.services.VehicleService;
import com.lghimfus.app.RCProject.utils.FormatUtils;

/**
 * Console application.
 *
 */
public class ConsoleApplication {
	public static void main( String[] args ) {
    	
	    // Holds the logic related to vehicles.
    	VehicleService vehicleService = 
    	    new VehicleService("http://www.rentalcars.com/js/vehicles.json");
    	
    	System.out.println("Console app - Part 1");
    	
    	System.out.println("\nTask 1 [The list of all the cars, in ascending price order]");
    	System.out.println(FormatUtils.formatAllByPrice(vehicleService.findAllOrderByPriceAsc()));
    	
    	System.out.println("\nTask 2 [Vehicles specifications]");
    	System.out.println(FormatUtils.formatAllBySpecs(vehicleService.findAll()));
    	
    	System.out.println("\nTask 3 [Highest rated suplier per car type]");
    	System.out.println(FormatUtils.formatAllBySupplier(vehicleService.findAllTypesOrderByHighestRatedSupplierDesc()));
    	
    	System.out.println("\nTask 4 [Combined score]");
    	System.out.println(FormatUtils.formatAllByScore(vehicleService.findAllOrderByCombinedScoreDesc()));
    }
    
}
        


