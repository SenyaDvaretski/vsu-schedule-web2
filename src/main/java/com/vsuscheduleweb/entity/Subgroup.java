package com.vsuscheduleweb.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;



@Data
@Accessors(chain = true)
@ToString
public class Subgroup {

    String id;

    List<Lesson> lessons = new ArrayList<>();
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
}
