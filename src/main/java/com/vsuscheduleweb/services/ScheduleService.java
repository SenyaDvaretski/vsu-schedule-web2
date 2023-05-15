package com.vsuscheduleweb.services;


import com.vsuscheduleweb.parser.Parser;
import com.vsuscheduleweb.parser.ParserException;
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


    private final Parser parser;


    public ResponseEntity<?> saveFile(MultipartFile multipartFile, String facult ,String path) {
        if (!multipartFile.isEmpty()) {
            try {
                new File(path  + facult ).mkdirs();
                File file = new File(path  + facult  +"\\"+ multipartFile.getOriginalFilename());
                if(file.exists()){
                    file.delete();
                };
                file = new File(path  + facult  +"\\"+ multipartFile.getOriginalFilename());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.close();
                try{
                    parser.parse(file,facult);
                    System.out.println(parser.getGroups());
                    System.out.println(parser.getTeachers());
                }catch (ParserException e){
                    System.out.println(e.getMessage());
                }
                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
            }catch (IOException e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
            }

        }else
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
    }

}

