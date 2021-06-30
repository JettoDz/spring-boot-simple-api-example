package restaurant_api.restaurant_api.exceptions;

import java.util.ArrayList;
import java.util.List;

import restaurant_api.restaurant_api.dtos.ErrorDTO;

public class BookingException extends Exception{

	private static final long serialVersionUID = 1L;

	private final String code;
	
	private int responseCode;
	
	private List<ErrorDTO> errorList = new ArrayList<>();

	public BookingException(String code, int responseCode, String msg) {
		super(msg);
		this.code = code;
		this.responseCode = responseCode;
	}

	public BookingException(String code, int responseCode, List<ErrorDTO> errorList, String msg) {
		super(msg);
		this.code = code;
		this.responseCode = responseCode;
		this.errorList = errorList;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public List<ErrorDTO> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorDTO> errorList) {
		this.errorList = errorList;
	}

	public String getCode() {
		return code;
	}
	
	
	
}
