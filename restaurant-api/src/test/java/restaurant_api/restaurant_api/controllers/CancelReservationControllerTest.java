package restaurant_api.restaurant_api.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.responses.BookingResponse;
import restaurant_api.restaurant_api.services.CancelReservationService;

public class CancelReservationControllerTest {
	
	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	private static final String LOCATOR = "Burger7";
	private static final String RESERVATION_DELETED = "LOCATOR DELETED";
	
	@Mock
	CancelReservationService cancelReservationService;
	@InjectMocks
	CancelReservationController cancelReservationController;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deleteReservationTest() throws BookingException {
		Mockito.when(cancelReservationService.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETED);
		final BookingResponse<String> response = cancelReservationController.deleteReservation(LOCATOR);
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMsg(), OK);
		assertEquals(response.getData(), RESERVATION_DELETED);
	}
	
}
