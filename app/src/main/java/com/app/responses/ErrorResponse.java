package com.app.responses;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {

    public static ResponseEntity<?> _forbidden(Object body){

        return new ResponseEntity<>(body, HttpStatusCode.valueOf(403));

    }

    public static ResponseEntity<?> _unauthorized(Object body){
        return new ResponseEntity<>(body, HttpStatusCode.valueOf(401));
    }

}
