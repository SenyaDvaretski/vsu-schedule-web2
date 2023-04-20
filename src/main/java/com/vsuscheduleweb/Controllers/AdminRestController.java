package com.vsuscheduleweb.Controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {


    // TODO method will uploading schedule from admin page
    public ResponseEntity uploadSchedule(){
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
