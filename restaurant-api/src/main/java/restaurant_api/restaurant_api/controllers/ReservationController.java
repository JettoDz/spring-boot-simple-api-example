package restaurant_api.restaurant_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restaurant_api.restaurant_api.exceptions.BookingException;
import restaurant_api.restaurant_api.jsons.CreateReservationRest;
import restaurant_api.restaurant_api.jsons.ReservationRest;
import restaurant_api.restaurant_api.responses.BookingResponse;
import restaurant_api.restaurant_api.services.ReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/restaurant" + "/v1")
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "reservation", method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> createReservation(@RequestBody CreateReservationRest reservation)
			throws BookingException {
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
				reservationService.createReservation(reservation));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "reservation" + "/{" + "id"	+ "}", method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<ReservationRest> getReservationById(@PathVariable int id) throws BookingException {
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
				reservationService.getReservation(id));
	}

}
