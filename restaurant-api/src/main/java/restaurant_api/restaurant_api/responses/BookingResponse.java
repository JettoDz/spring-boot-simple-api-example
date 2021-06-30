package restaurant_api.restaurant_api.responses;

import java.io.Serializable;

public class BookingResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String code;
	private String msg;
	private T data;
	
	public BookingResponse(String status, String code, String msg, T data) {
		this.status = status;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public BookingResponse(String status, String code, String msg) {
		this.status = status;
		this.code = code;
		this.msg = msg;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
