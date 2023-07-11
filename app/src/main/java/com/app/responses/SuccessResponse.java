package com.app.responses;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class SuccessResponse {

    public static ResponseEntity<?> _success(Object body){

        return new ResponseEntity<>(body, HttpStatusCode.valueOf(200));

    }

}
