package io.quarkus;

import io.quarkus.dto.CarDto;
import io.quarkus.services.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.UUID;

@Path("/cars")
public class GreetingResource {

    private final Logger logger = LoggerFactory.getLogger(GreetingResource.class);
    private final ResourceService resourceService;

    @Inject
    public GreetingResource(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        logger.debug("Env variables: [{}]", System.getenv().toString());
        return Response.ok(resourceService.getCars()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar() {
        final CarDto carDto = new CarDto(true, LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID().toString());
        return Response.ok(resourceService.createResource(carDto)).build();
    }
}
