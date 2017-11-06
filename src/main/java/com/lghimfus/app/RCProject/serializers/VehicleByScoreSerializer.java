package com.lghimfus.app.RCProject.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lghimfus.app.RCProject.models.Vehicle;

public class VehicleByScoreSerializer extends JsonSerializer<Vehicle>{

    @Override
    public void serialize(Vehicle vehicle, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
      jsonGenerator.writeStartObject();
      jsonGenerator.writeStringField("name", vehicle.getName());
      jsonGenerator.writeNumberField("score", vehicle.getVehicleSpecs().getScore());
      jsonGenerator.writeNumberField("rating", vehicle.getRating());
      jsonGenerator.writeNumberField("combinedScore", 
          vehicle.getVehicleSpecs().getScore() + vehicle.getRating());
      jsonGenerator.writeEndObject();    
    }

}
