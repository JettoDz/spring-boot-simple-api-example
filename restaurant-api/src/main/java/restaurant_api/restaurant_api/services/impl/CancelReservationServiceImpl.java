package restaurant_api.restaurant_api.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.exceptions.InternalServerErrorException;
import restaurant_api.restaurant_api.exceptions.NotFoundException;
import restaurant_api.restaurant_api.repositories.ReservationRepository;
import restaurant_api.restaurant_api.services.CancelReservationService;

@Service
public class CancelReservationServiceImpl implements CancelReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	ReservationRepository reservationRepository;

	public String deleteReservation(String locator) throws BookingException {
		reservationRepository.findByLocator(locator)
				.orElseThrow(() -> new NotFoundException("RESERVATION NOT FOUND", "RESERVATION NOT FOUND"));

		try {
			reservationRepository.deleteByLocator(locator);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("INTERNAL SERVER ERROR", "INTERNAL SERVER ERROR");
		}

		return "LOCATOR DELETED";
	}

}
