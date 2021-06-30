package restaurant_api.restaurant_api.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant_api.restaurant_api.entities.Reservation;
import restaurant_api.restaurant_api.entities.Restaurant;
import restaurant_api.restaurant_api.entities.Turn;
import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.exceptions.InternalServerErrorException;
import restaurant_api.restaurant_api.exceptions.NotFoundException;
import restaurant_api.restaurant_api.jsons.CreateReservationRest;
import restaurant_api.restaurant_api.jsons.ReservationRest;
import restaurant_api.restaurant_api.repositories.ReservationRepository;
import restaurant_api.restaurant_api.repositories.RestaurantRepository;
import restaurant_api.restaurant_api.repositories.TurnRepository;
import restaurant_api.restaurant_api.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
 
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	TurnRepository turnRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public ReservationRest getReservation(int id) throws BookingException {
		return modelMapper.map(getReservationEntity(id), ReservationRest.class);
	}

	private Reservation getReservationEntity(int id) throws BookingException {
		return reservationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT NOT FOUND"));
	}

	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {

		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("RESTAURANT NOT FOUND", "RESTAURANT NOT FOUND"));
		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFoundException("TURN NOT FOUND", "TURN NOT FOUND"));
		String locator = generateLocator(restaurantId, createReservationRest);
		
		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());
		
		try{
			reservationRepository.save(reservation);
		} catch (final Exception e){
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("INTERNAL SERVER ERROR", "INTERNAL SERVER ERROR");
		}
		return locator;
	}

	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservation)
			throws BookingException {
		return restaurantId.getName() + createReservation.getTurnId();
	}

}
