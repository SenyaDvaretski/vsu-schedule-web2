package com.vsuscheduleweb.entity;


import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


//одно занятие с его временем, аудиторией и датой
@Data
@Accessors(chain = true)
@ToString
public class Lesson {
    String startTime;

    String endTime;

    String auditorium;

    String date;

    String weekDay;

    List<Teacher>  teachers = new ArrayList<>();

    String name;

    String type;

    public Lesson addTeacher(Teacher teacher){
        teachers.add(teacher);
        return this;
    }

}
