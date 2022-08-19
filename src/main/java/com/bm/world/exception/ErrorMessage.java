package com.bm.world.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    String statusCode;
    String message;

    public ErrorMessage(String message) {
        this.message = message;
    }      
}
