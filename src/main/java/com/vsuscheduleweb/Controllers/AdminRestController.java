package com.vsuscheduleweb.Controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
public class AdminRestController {


    // TODO method will uploading schedule from admin page
    @PostMapping("/rest/uploadFile")
    public ResponseEntity uploadSchedule(@RequestPart MultipartFile file){
        System.out.println(file.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
