package br.com.uniacademia.enade.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ResponseTO {
	
	@JsonIgnore
	private Integer httpStatus = 200;
	
	private Integer code;
	private Integer size;
	private Object content;
	private List<?> contents;
	
	public ResponseTO(Integer code) {
		this.code = code;
	}
	
	public ResponseTO(CustomExceptionTO e) {
		this.code = e.getCode();
		this.httpStatus =e.getHttpStatus();
		this.content = e.getMessage();
		this.contents = e.getErrors();
	}
	
	public ResponseTO(Integer code, Integer HttpStatus) {
		this.code = code;
		this.httpStatus = HttpStatus;
	}
	
	public ResponseTO(Integer code, Object content) {
		this.code = code;
		this.content = content;
	}
	
	public ResponseTO(Integer code, Integer HttpStatus, Object content) {
		this.code = code;
		this.httpStatus = HttpStatus;
		this.content = content;
	}
	
	public ResponseTO(Integer code, List<?> contents) {
		this.code = code;
		this.size = contents.size();
		this.contents = contents;
	}
	
	public ResponseTO(Integer code, Integer HttpStatus, List<?> contents) {
		this.code = code;
		this.httpStatus = HttpStatus;
		this.size = contents.size();
		this.contents = contents;
	}
	
}
