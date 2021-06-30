package restaurant_api.restaurant_api.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import restaurant_api.restaurant_api.dtos.ErrorDTO;

public class InternalServerErrorException extends BookingException {

	private static final long serialVersionUID = 1L;

	public InternalServerErrorException(String code, String msg) {
		super(code, HttpStatus.NOT_FOUND.value(), msg);
	}
	
	public InternalServerErrorException(String code, String msg, ErrorDTO data) {
		super(code, HttpStatus.NOT_FOUND.value(), Arrays.asList(data), msg);
	}
}
