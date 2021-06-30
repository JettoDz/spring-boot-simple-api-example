package restaurant_api.restaurant_api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
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
import restaurant_api.restaurant_api.repositories.ReservationRepository;
import restaurant_api.restaurant_api.services.impl.CancelReservationServiceImpl;

public class CancelReservationServiceTest {

	@Mock
	ReservationRepository reservationRepository;
	@InjectMocks
	CancelReservationServiceImpl cancelReservationServiceImpl;
	
	private static final String RESERVATION_DELETED = "LOCATOR DELETED";
	private static final LocalDate DATE	= LocalDate.now();
	private static final String LOCATOR = "Burger7";
	private static final int PERSONS = 1;
	private static final int RESTAURANT_ID = 1;
	private static final String TURN = "TURNO_10_00";
	private static final String NAME = "Tester";
	private static final String DESCRIPTION = "Testing desc";
	private static final String ADDRESS = "Avenida T";
	private static final String IMAGE = "www.image.com";
	
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final Reservation RESERVATION = new Reservation();
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();
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
		
		RESERVATION.setDate(DATE);
		RESERVATION.setLocator(LOCATOR);
		RESERVATION.setPerson(PERSONS);
		RESERVATION.setRestaurant(RESTAURANT);
		RESERVATION.setTurn(TURN);
		
	}
	
	@Test
	public void deleteReservationTest() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
	}
	
	@Test(expected = BookingException.class)
	public void deleteReservationNoLocatorTest() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		cancelReservationServiceImpl.deleteReservation(LOCATOR);
		fail();
	}
	
	@Test(expected = BookingException.class)
	public void deleteReservationFailedTest() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		Mockito.doThrow(Exception.class).when(reservationRepository).deleteByLocator(LOCATOR);
		cancelReservationServiceImpl.deleteReservation(LOCATOR);
		fail();
	}
	
	
	
}
