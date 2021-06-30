package restaurant_api.restaurant_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.jsons.RestaurantRest;
import restaurant_api.restaurant_api.responses.BookingResponse;
import restaurant_api.restaurant_api.services.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/restaurant" + "/v1")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "restaurant" + "/{" + "id" + "}", method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<RestaurantRest> getRestaurantById(@PathVariable int id) throws BookingException{
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", 
				restaurantService.getRestaurantById(id));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "restaurants", method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<List<RestaurantRest>> getRestaurants() throws BookingException{
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", 
				restaurantService.getRestaurants());
	}

}
