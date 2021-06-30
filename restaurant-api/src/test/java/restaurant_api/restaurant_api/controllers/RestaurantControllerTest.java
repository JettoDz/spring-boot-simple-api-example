package restaurant_api.restaurant_api.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.jsons.RestaurantRest;
import restaurant_api.restaurant_api.jsons.TurnRest;
import restaurant_api.restaurant_api.responses.BookingResponse;
import restaurant_api.restaurant_api.services.RestaurantService;

public class RestaurantControllerTest {

	private static final int RESTAURANT_ID = 1;
	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	
	private static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
	private static final String NAME = "Tester";
	private static final String DESCRIPTION = "Testing desc";
	private static final String ADDRESS = "Avenida T";
	private static final String IMAGE = "www.image.com";
	private static final List<TurnRest> TURN_LIST = new ArrayList<>();
	
	private static final List<RestaurantRest> RESTAURANT_LIST = new ArrayList<>();
	
	@Mock
	RestaurantService restaurantService;
	@InjectMocks
	RestaurantController restaurantController;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		RESTAURANT_REST.setName(NAME);
		RESTAURANT_REST.setAddress(ADDRESS);
		RESTAURANT_REST.setDescription(DESCRIPTION);
		RESTAURANT_REST.setImage(IMAGE);
		RESTAURANT_REST.setId(RESTAURANT_ID);
		RESTAURANT_REST.setTurns(TURN_LIST);
		
		Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
	}

	@Test
	public void getRestaurantByIdTest() throws BookingException {
		final BookingResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMsg(), OK);
		assertEquals(response.getData(), RESTAURANT_REST);
	}
	
	@Test
	public void getRestaurants() throws BookingException {
		final BookingResponse<List<RestaurantRest>> response = restaurantController.getRestaurants();
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMsg(), OK);
		assertEquals(response.getData(), RESTAURANT_LIST);
	}

}
