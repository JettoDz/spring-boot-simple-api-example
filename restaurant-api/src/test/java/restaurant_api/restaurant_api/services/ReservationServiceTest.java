package restaurant_api.restaurant_api.services;

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
import restaurant_api.restaurant_api.jsons.CreateReservationRest;
import restaurant_api.restaurant_api.jsons.ReservationRest;
import restaurant_api.restaurant_api.repositories.ReservationRepository;
import restaurant_api.restaurant_api.repositories.RestaurantRepository;
import restaurant_api.restaurant_api.repositories.TurnRepository;
import restaurant_api.restaurant_api.services.impl.ReservationServiceImpl;

public class ReservationServiceTest {

	@Mock
	ReservationRepository reservationRepository;
	@Mock
	RestaurantRepository restaurantRepository;
	@Mock
	TurnRepository turnRepository;
	@InjectMocks
	ReservationServiceImpl reservationServiceImpl;
	
	private static final int RESERVATION_ID = 1;
	private static final LocalDate DATE = LocalDate.now();
	private static final int PERSONS = 1;
	private static final int RESTAURANT_ID = 1;
	private static final int TURN_ID = 1;
	private static final String TURN_NAME = "TURNO_10_00";
	private static final String LOCATOR = "Burger1";
	
	private static final String NAME = "Tester";
	private static final String DESCRIPTION = "Testing desc";
	private static final String ADDRESS = "Avenida T";
	private static final String IMAGE = "www.image.com";
	private static final List<Turn> TURN_LIST = new ArrayList<>();
	private static final List<Board> BOARD_LIST = new ArrayList<>();
	private static final List<Reservation> RESERVATION_LIST = new ArrayList<>();
	
	private static final ReservationRest RESERVATIONREST = new ReservationRest();
	private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final Optional<Restaurant> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);
	private static final Optional<Restaurant> OPTIONAL_RESTAUTANT_EMPTY = Optional.empty();
	private static final Turn TURN = new Turn();
	private static final Optional<Turn> OPTIONAL_TURN = Optional.of(TURN);
	private static final Optional<Turn> OPTIONAL_TURN_EMTPY = Optional.empty();
	private static final Reservation RESERVATION = new Reservation();
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();
	
	@Before
	public void init() throws BookingException{
		MockitoAnnotations.initMocks(this);
		
		RESTAURANT.setName(NAME);
		RESTAURANT.setAddress(ADDRESS);
		RESTAURANT.setDescription(DESCRIPTION);
		RESTAURANT.setImage(IMAGE);
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setTurns(TURN_LIST);
		RESTAURANT.setBoards(BOARD_LIST);
		RESTAURANT.setReservations(RESERVATION_LIST);
		
		TURN.setId(TURN_ID);
		TURN.setName(TURN_NAME);
		TURN.setRestaurant(RESTAURANT);
		
		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSONS);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		
		RESERVATIONREST.setDate(DATE);
		RESERVATIONREST.setId(RESERVATION_ID);
		RESERVATIONREST.setLocator(LOCATOR);
		RESERVATIONREST.setPerson(PERSONS);
		RESERVATIONREST.setRestaurantId(RESTAURANT_ID);
		RESERVATIONREST.setTurn(TURN_NAME);
	}
	
	@Test
	public void getReservationTest() throws BookingException {
		Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESERVATION);
		reservationServiceImpl.getReservation(RESERVATION_ID);
	}
	
	@Test(expected = BookingException.class)
	public void getReservationNoReservationTest() throws BookingException {
		Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		reservationServiceImpl.getReservation(RESERVATION_ID);
		fail();
	}
	
	@Test
	public void createReservationTest() throws BookingException{
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
		Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
	}
	
	@Test(expected = BookingException.class)
	public void createReservationNoRestaurantTest() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAUTANT_EMPTY);
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
		fail();
	}
	
	@Test(expected = BookingException.class)
	public void createReservationNoTurnTest() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN_EMTPY);
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
		fail();
	}
	
	@Test(expected = BookingException.class)
	public void createReservationFailedSaveTest() throws BookingException{
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
		Mockito.doThrow(Exception.class).when(reservationRepository).save(Mockito.any(Reservation.class));
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
		fail();
	}
	
}
