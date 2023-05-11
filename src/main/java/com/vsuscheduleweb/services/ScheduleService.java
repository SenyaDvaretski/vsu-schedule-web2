package com.vsuscheduleweb.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@RequiredArgsConstructor
public class ScheduleService {


    public ResponseEntity<?> saveFile(MultipartFile multipartFile, String facult ,String path) {
        if (!multipartFile.isEmpty()) {
            try {
                multipartFile.transferTo(new File(path  + facult  +"\\"+ multipartFile.getOriginalFilename()));
                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
            }catch (IOException e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
            }

        }else
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
    }

}

