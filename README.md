# Rentalcars Graduate Technical Test

## How to use
Clone the repository
```git clone https://github.com/lghimfus/RCProject.git```

Use [Maven](https://maven.apache.org/) to **build** the project ```mvn clean package```

Use [Maven](https://maven.apache.org/) to **test** the code ```mvn test```


### Run part 1 - Console application
```
java -cp target/RCProject-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.lghimfus.app.RCProject.ConsoleApplication
```

### Run part 2 - REST application
```
java -cp target/RCProject-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.lghimfus.app.RCProject.RestApplication
```

#### REST application endpoints
```
localhost:4567/vehiclesByPrice
localhost:4567/vehiclesBySpecs
localhost:4567/vehiclesBySupplierRating
localhost:4567/vehiclesByScore
```
**NOTE:** 

The above endpoints return **text/plain response** containing the same output as the console application.
However, each endpoint supports the query parameter `json` which recognizes two values: `full` and `custom`.
* `?json=full` returns an **application/json response** containing the JSON representation of a list of vehicles where a vehicle node contains all the fields available in a Vehicle class.
* `?json=custom` returns an **application/json response** containing the JSON representation of a list of vehicles, but a vehicle node contains just the fields required for printing in part 1 (tasks 1-4). 

**_Additional possible requests_**
```
localhost:4567/vehiclesByPrice?json=full
localhost:4567/vehiclesByPrice?json=custom

localhost:4567/vehiclesBySpecs?json=full
localhost:4567/vehiclesBySpecs?json=custom

localhost:4567/vehiclesBySupplierRating?json=full
localhost:4567/vehiclesBySupplierRating?json=custom

localhost:4567/vehiclesByScore?json=full
localhost:4567/vehiclesByScore?json=custom

localhost:4567/vehicles
```


## Built with 
* [Maven](https://maven.apache.org/)

## Source tree
* [src/main/java/com/lghimfus/app/RCProject](./src/main/java/com/lghimfus/app/RCProject)
  * [ConsoleApplication.java](./src/main/java/com/lghimfus/app/RCProject/ConsoleApplication.java)
  * [RestApplication.java](./src/main/java/com/lghimfus/app/RCProject/RestApplication.java)
  * [controllers](./src/main/java/com/lghimfus/app/RCProject/controllers)
    * [VehicleController.java](./src/main/java/com/lghimfus/app/RCProject/controllers/VehicleController.java)
  * [models](./src/main/java/com/lghimfus/app/RCProject/models)
    * [SippSpecs.java](./src/main/java/com/lghimfus/app/RCProject/models/SippSpecs.java)
    * [Vehicle.java](./src/main/java/com/lghimfus/app/RCProject/models/Vehicle.java)
    * [VehicleSpecs.java](./src/main/java/com/lghimfus/app/RCProject/models/VehicleSpecs.java)
  * [serializers](./src/main/java/com/lghimfus/app/RCProject/serializers)
    * [VehicleByPriceSerializer.java](./src/main/java/com/lghimfus/app/RCProject/serializers/VehicleByPriceSerializer.java)
    * [VehicleByScoreSerializer.java](./src/main/java/com/lghimfus/app/RCProject/serializers/VehicleByScoreSerializer.java)
    * [VehicleBySpecsSerializer.java](./src/main/java/com/lghimfus/app/RCProject/serializers/VehicleBySpecsSerializer.java)
    * [VehicleBySupplierSerializer.java](./src/main/java/com/lghimfus/app/RCProject/serializers/VehicleBySupplierSerializer.java)
  * [services](./src/main/java/com/lghimfus/app/RCProject/services)
    * [SippSpecsService.java](./src/main/java/com/lghimfus/app/RCProject/services/SippSpecsService.java)
    * [VehicleService.java](./src/main/java/com/lghimfus/app/RCProject/services/VehicleService.java)
  * [utils](./src/main/java/com/lghimfus/app/RCProject/utils)
    * [FormatUtils.java](./src/main/java/com/lghimfus/app/RCProject/utils/FormatUtils.java)
    * [NetworkUtils.java/lghimfus/app/RCProject/utils](./src/main/java/com/lghimfus/app/RCProject/utils/NetworkUtils.java)

* [src/main/resources](./src/main/resources)
  * [json](./src/main/resources/json)
    * [car_types.json](./src/main/resources/json/car_types.json)
    * [door_types.json](./src/main/resources/json/door_types.json)
    * [fuel_ac_types.json](./src/main/resources/json/fuel_ac_types.json)
    * [transmission_types.json](./src/main/resources/json/transmission_types)
  * [log4j.xml](./src/main/resources/json/log4j.xml)

* [src/test/java/com.lghimfus/app/RCProject](./src/test/java/com.lghimfus/app/RCProject)
  * [controllers](./src/test/java/com/lghimfus/app/RCProject/controllers)
    * [VehicleController.java](./src/test/java/com/lghimfus/app/RCProject/controllers/VehiclesControllerTest.java)
  * [serializers](./src/test/java/com/lghimfus/app/RCProject/serializers)
    * [JacksonSerializerTest.java](./src/test/java/com/lghimfus/app/RCProject/controllers/JacksonSerializerTest.java)
  * [services](./src/test/java/com/lghimfus/app/RCProject/services)
    * [SippSpecsServiceTest.java](./src/test/java/com/lghimfus/app/RCProject/controllers/SippSpecsServiceTest.java)
    * [VehicleServiceTest.java](./src/test/java/com/lghimfus/app/RCProject/controllers/VehicleServiceTest.java)
  * [utils](./src/test/java/com/lghimfus/app/RCProject/utils)
    * [FormatUtilsTest.java](./src/test/java/com/lghimfus/app/RCProject/controllers/FormatUtilsTest.java)

* [src/test/resources](./src/test/resources)
  * [vehiclesByPrice.json](./src/test//resources/vehiclesByPrice.json)
  * [vehiclesByScore.json](./src/test//resources/vehiclesByScore.json)
  * [vehiclesBySpecs.json](./src/test//resources/vehiclesBySpecs.json)
  * [vehiclesBySupplierRating.json](./src/main//resources/vehiclesBySupplierRating.json)
