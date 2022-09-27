package com.bm.world.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StudentRequest {
	private long studentId;
    @NotBlank(message = "first name is mandatory field")
    private String firstName;

    private String middleName;

    private String lastName;

    private String emailId;

    private String mobileNumber;
}
