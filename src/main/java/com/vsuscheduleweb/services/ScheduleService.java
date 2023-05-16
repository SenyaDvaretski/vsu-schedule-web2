package com.vsuscheduleweb.services;


import com.vsuscheduleweb.Exceptions.Errors.AppError;
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


    public ResponseEntity<?> saveFile(MultipartFile multipartFile, String facult ) {
        String path = System.getProperty("user.dir") + "\\src\\main\\temp";
        if (!multipartFile.isEmpty()) {
            try {
                new File(path ).mkdirs();
                System.out.println(path);
                File file = new File(path   +"\\"+ multipartFile.getOriginalFilename());
                if(file.exists()) file.delete();
                file = new File(path +"\\"+ multipartFile.getOriginalFilename());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.close();
                try{
                    parser.parse(file,facult);
                }catch (ParserException e){
                    return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()),HttpStatus.BAD_REQUEST);
                }

                System.out.println(parser.getGroups());
                System.out.println(parser.getTeachers());



                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
            }

        }else
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
    }

}

