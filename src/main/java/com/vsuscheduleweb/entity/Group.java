package com.vsuscheduleweb.entity;


import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;



@Data
@Accessors(chain = true)
@ToString
public class Group {
    String id;

    List<Lesson> commonLessons = new ArrayList<>();

    List<Subgroup> subgroups = new ArrayList<>();

    String name;

    int countOfSubGroups; // transitional

    public void addLesson(Lesson lesson){
        commonLessons.add(lesson);
    }

    public void addSubgroup(Subgroup subgroup){
        subgroups.add(subgroup);
    }

}
