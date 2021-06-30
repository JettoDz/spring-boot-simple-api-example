package restaurant_api.restaurant_api.controllers;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.jsons.CreateReservationRest;
import restaurant_api.restaurant_api.jsons.ReservationRest;
import restaurant_api.restaurant_api.responses.BookingResponse;
import restaurant_api.restaurant_api.services.ReservationService;

public class ReservationControllerTest {
	
	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	
	private static final LocalDate DATE = LocalDate.now();
	private static final int RESTAURANT_ID = 1;
	private static final int PERSONS = 1;
	private static final int TURN_ID = 1;
	private static final String TURN = "TURNO_10_00";
	private static final String LOCATOR = "BurgerHall1";
	private static final int RESERVATION_ID = 1;
	
	private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	private static final ReservationRest RESERVATION_REST = new ReservationRest();
	
	@Mock
	ReservationService reservationService;
	@InjectMocks
	ReservationController reservationController;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		
		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSONS);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);

		RESERVATION_REST.setDate(DATE);
		RESERVATION_REST.setLocator(LOCATOR);
		RESERVATION_REST.setPerson(PERSONS);
		RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		RESERVATION_REST.setTurn(TURN);
		
		Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
		Mockito.when(reservationService.getReservation(RESERVATION_ID)).thenReturn(RESERVATION_REST);
	}
	
	@Test
	public void createReservationTest() throws BookingException {
		final BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMsg(), OK);
		assertEquals(response.getData(), LOCATOR);
	}
	
	@Test
	public void getReservationByIdTest() throws BookingException {
		final BookingResponse<ReservationRest> response = reservationController.getReservationById(RESERVATION_ID);
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMsg(), OK);
		assertEquals(response.getData(), RESERVATION_REST);
	}
	
	
	
}
