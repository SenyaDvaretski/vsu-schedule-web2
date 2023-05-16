package com.vsuscheduleweb.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


//одно занятие с его временем, аудиторией и датой
@Data
@Accessors(chain = true)
@ToString
@Entity(name = "lessons")
public class Lesson {

    @Id
    @Column(name = "lesson_id")
    private UUID id;
    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    private String auditorium;


    private String date;

    @Column(name = "weekday")
    private String weekDay;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Teacher>  teachers = new ArrayList<>();

    @Column(name = "lesson_name")
    private String name;

    private String type;

    public Lesson addTeacher(Teacher teacher){
        teachers.add(teacher);
        return this;
    }

}
