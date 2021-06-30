package restaurant_api.restaurant_api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant_api.restaurant_api.entities.Restaurant;
import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.exceptions.NotFoundException;
import restaurant_api.restaurant_api.jsons.RestaurantRest;
import restaurant_api.restaurant_api.repositories.RestaurantRepository;
import restaurant_api.restaurant_api.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;

	public static final ModelMapper modelMapper = new ModelMapper();
	
	public RestaurantRest getRestaurantById(int restaurantId) throws BookingException {
		return modelMapper.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
	}
	
	private Restaurant getRestaurantEntity(int restaurantId) throws BookingException {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT NOT FOUND"));
	}

	public List<RestaurantRest> getRestaurants() throws BookingException {
		List<Restaurant> restaurantEntities = restaurantRepository.findAll();
		return restaurantEntities.stream()
								 .map(restaurant -> modelMapper.map(restaurant, RestaurantRest.class))
								 .collect(Collectors.toList());
	}

}
