package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;

@Path("cars")
public class CarFakeRESTService {	
	
	@GET
	@Path("/{carId}")
	@Produces("application/json")
	public Car getCar(@PathParam("carId") Long id){
		Person p = cm.getCars(id);
		return p;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Car car) {
 
		String result = "Car saved: " + car;
		return Response.status(201).entity(result).build(); 
	}

}
