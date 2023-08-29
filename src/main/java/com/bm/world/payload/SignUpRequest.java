package com.bm.world.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Setter
@Getter



@Data
public class SignUpRequest {
    @NotBlank
    @Size(max = 45,min = 10,message = "userName should be greater than 10 less then 45 character")
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;
    private String[] roles;

}
