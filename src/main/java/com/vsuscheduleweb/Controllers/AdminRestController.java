package com.vsuscheduleweb.Controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
public class AdminRestController {



    @PostMapping("/rest/uploadFile")
    public ResponseEntity uploadSchedule(@RequestPart MultipartFile file,
                                         @RequestParam("f") String fac){
        System.out.println(file.getOriginalFilename() + fac);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
