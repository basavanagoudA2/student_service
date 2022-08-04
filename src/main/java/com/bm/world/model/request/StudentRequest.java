package com.bm.world.model.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class StudentRequest {
    @NotBlank(message = "first name is mandatory field")
    private String firstName;

    private String middleName;

    private String lastName;

    private String emailId;

    private String mobileNumber;
}
