package com.bm.world.exception;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@Setter
@Getter
public class StudentNotFoundException extends RuntimeException{

	public StudentNotFoundException() {
	}

	public StudentNotFoundException(String message) {
		super(message);
	}
}
