package com.vsuscheduleweb.Controllers;

import com.vsuscheduleweb.entity.Group;
import com.vsuscheduleweb.entity.Lesson;
import com.vsuscheduleweb.entity.Subgroup;
import com.vsuscheduleweb.entity.Teacher;
import com.vsuscheduleweb.repositories.GroupRepository;
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
@RequestMapping("/rest/groups")
@RestController()
public class GroupController {

    private final GroupRepository groupRepository;

    @GetMapping()
    public ResponseEntity<List<Group>> getTeachers(){
        List<Group> groups = groupRepository.findAll();

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

}
