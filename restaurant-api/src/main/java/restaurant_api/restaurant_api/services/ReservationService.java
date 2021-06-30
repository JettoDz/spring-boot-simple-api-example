package restaurant_api.restaurant_api.services;

import org.springframework.stereotype.Service;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.jsons.CreateReservationRest;
import restaurant_api.restaurant_api.jsons.ReservationRest;

@Service
public interface ReservationService {

	ReservationRest getReservation(int id) throws BookingException;
	
	String createReservation(CreateReservationRest createReservationRest) throws BookingException;
}
