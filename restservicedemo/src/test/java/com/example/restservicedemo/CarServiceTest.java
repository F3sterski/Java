package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.* ;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

public class CarServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";
	}
	
	@Test
	public void getCar(){
		get("/cars/0").then().assertThat().body("model", equalTo("Corsa"));
		
		
		Car aCar = get("/cars/0").as(Car.class);
		assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
	}
	
	@Test
	public void addCar(){
		
		Car aCar = new Car(2, "Ford", "Fiesta", 2011);
		given().
		       contentType("application/json; charset=UTF-16").
		       body(aCar).
		when().	     
		post("/cars/").then().assertThat().statusCode(201).body(containsString("Car saved:"));
	}
	
	@Test 
	public void AllPersons(){
		PersonManager pm = new PersonManager();
		Person fake = new Person("Adam",1990);
		pm.addPerson(fake);
		List<Person> list = pm.getAllPersons();
		for(int i=0;i<list.size();i++){
		    get("/persons/"+list.get(i).getId()).
		    then().assertThat().
		    body("firstName", equalTo(list.get(i).getFirstName()));
		}
	}
	

}
