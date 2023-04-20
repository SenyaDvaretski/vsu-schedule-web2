package com.vsuscheduleweb.entity;


import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;



@Data
@Accessors(chain = true)
@ToString
public class Teacher {
    String firstname;
    String lastname;
    String surname;
    String initials;
    List<Lesson> lessons = new ArrayList<>();
    String qualification;
    public Teacher addLesson(Lesson lesson){

        return this;
    }

}
