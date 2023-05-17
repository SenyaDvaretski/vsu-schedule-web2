package com.vsuscheduleweb.Controllers;


import com.vsuscheduleweb.entity.Teacher;
import com.vsuscheduleweb.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/rest/teachers")
@RestController()
public class TeachersController {

    private final TeacherRepository teacherRepository;

    @GetMapping()
    public ResponseEntity<List<Teacher>> getTeachers(){
        List<Teacher> teacherList = teacherRepository.findAll();

        return new ResponseEntity<>(teacherList,HttpStatus.OK);
    }

}
