package restaurant_api.restaurant_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.jsons.RestaurantRest;

@Service
public interface RestaurantService {
	
	RestaurantRest getRestaurantById(int restaurantId) throws BookingException;
	
	List<RestaurantRest> getRestaurants() throws BookingException;
}
