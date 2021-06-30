package restaurant_api.restaurant_api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import restaurant_api.restaurant_api.entities.Board;
import restaurant_api.restaurant_api.entities.Reservation;
import restaurant_api.restaurant_api.entities.Restaurant;
import restaurant_api.restaurant_api.entities.Turn;
import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.jsons.RestaurantRest;
import restaurant_api.restaurant_api.repositories.RestaurantRepository;
import restaurant_api.restaurant_api.services.impl.RestaurantServiceImpl;

public class RestaurantServiceTest {

	@Mock
	RestaurantRepository restaurantRepository;
	@InjectMocks
	RestaurantServiceImpl restaurantServiceImpl;
	
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final int RESTAURANT_ID = 1;
	
	private static final String NAME = "Tester";
	private static final String DESCRIPTION = "Testing desc";
	private static final String ADDRESS = "Avenida T";
	private static final String IMAGE = "www.image.com";
	private static final List<Turn> TURN_LIST = new ArrayList<>();
	private static final List<Board> BOARD_LIST = new ArrayList<>();
	private static final List<Reservation> RESERVATION_LIST = new ArrayList<>();
	
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		RESTAURANT.setName(NAME);
		RESTAURANT.setAddress(ADDRESS);
		RESTAURANT.setDescription(DESCRIPTION);
		RESTAURANT.setImage(IMAGE);
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setTurns(TURN_LIST);
		RESTAURANT.setBoards(BOARD_LIST);
		RESTAURANT.setReservations(RESERVATION_LIST);
	}
	
	@Test
	public void getRestaurantByIdTest() throws BookingException{
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
		restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
	}
	
	@Test(expected = BookingException.class)
	public void getRestaurantByIdFailTest() throws BookingException{
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
		restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
	}
	
	@Test
	public void getRestaurants() throws BookingException{
		final Restaurant restaurant = new Restaurant();
		Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
		final List<RestaurantRest> response = restaurantServiceImpl.getRestaurants();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}
}
