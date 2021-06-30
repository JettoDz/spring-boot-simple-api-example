package restaurant_api.restaurant_api.services;

import org.springframework.stereotype.Service;

import restaurant_api.restaurant_api.exceptions.BookingException;

@Service
public interface CancelReservationService {
	
	public String deleteReservation(String locator) throws BookingException;
}
