package com.bm.world.exception;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
	
	private String errorMessage;
	private LocalTime errorTime;
	private Long errorCode;
	

}
