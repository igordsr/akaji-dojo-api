package br.com.akaji.dojo.exception;

import java.util.Date;
import java.util.Set;

public class ApiErrorHandlerDto {
	private Date timestamp;
	private Integer status;
	private String code;
	private Set<ErrorHandlerDto> errors;

	public ApiErrorHandlerDto() {
	}

	public ApiErrorHandlerDto(Date timestamp, Integer status, String code, Set<ErrorHandlerDto> errors) {
		this.timestamp = timestamp;
		this.status = status;
		this.code = code;
		this.errors = errors;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<ErrorHandlerDto> getErrors() {
		return errors;
	}

	public void setErrors(Set<ErrorHandlerDto> errors) {
		this.errors = errors;
	}

}
