package com.lghimfus.app.RCProject.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lghimfus.app.RCProject.models.Vehicle;

public class VehicleBySpecsSerializer extends JsonSerializer<Vehicle>{

    @Override
    public void serialize(Vehicle vehicle, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
      jsonGenerator.writeStartObject();
      jsonGenerator.writeStringField("name", vehicle.getName());
      jsonGenerator.writeStringField("sipp", vehicle.getSipp());
      jsonGenerator.writeStringField("carType", vehicle.getVehicleSpecs().getCarType());
      jsonGenerator.writeStringField("doorsType", vehicle.getVehicleSpecs().getDoorsType());
      jsonGenerator.writeStringField("transmission", vehicle.getVehicleSpecs().getTransmission());
      jsonGenerator.writeStringField("fuel", vehicle.getVehicleSpecs().getFuel());
      jsonGenerator.writeStringField("airCon", vehicle.getVehicleSpecs().getAirCon());
      jsonGenerator.writeEndObject();    
    }

}
