package com.vsuscheduleweb.services;


import com.vsuscheduleweb.Exceptions.Errors.AppError;
import com.vsuscheduleweb.entity.Group;
import com.vsuscheduleweb.entity.Lesson;
import com.vsuscheduleweb.entity.Subgroup;
import com.vsuscheduleweb.entity.Teacher;
import com.vsuscheduleweb.parser.Parser;
import com.vsuscheduleweb.parser.ParserException;
import com.vsuscheduleweb.repositories.GroupRepository;
import com.vsuscheduleweb.repositories.LessonRepository;
import com.vsuscheduleweb.repositories.SubgroupRepository;
import com.vsuscheduleweb.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.io.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {


    private final Parser parser;

    private final TeacherRepository teacherRepository;

    private final GroupRepository groupRepository;

    private final LessonRepository lessonRepository;

    private final SubgroupRepository subgroupRepository;





    public ResponseEntity<?> saveFile(MultipartFile multipartFile, String facult ) {
        String path = System.getProperty("user.dir") + "\\src\\main\\temp";
        lessonRepository.deleteAllWhereFacultEquals(facult);
        if (!multipartFile.isEmpty()) {
            try {
                new File(path).mkdirs();
                File file = new File(path   +"\\"+ multipartFile.getOriginalFilename());
                if(file.exists()) file.delete();
                file = new File(path +"\\"+ multipartFile.getOriginalFilename());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.close();

                try{
                    parser.parse(file,facult);

                    List<Group> groups = parser.getGroups();
                    List<Lesson> lessons = parser.getLessons();
                    for(Lesson lesson : lessons){
                        lesson.setFacult(facult);
                    }
                    for(Group group : groups){
                        for(Lesson lesson : group.getCommonLessons()){
                            lessonRepository.save(lesson);
                        }
                        for(Subgroup subgroup: group.getSubgroups()){
                            for(Lesson lesson : subgroup.getLessons()){
                                lessonRepository.save(lesson);

                            }
                            subgroupRepository.save(subgroup);
                        }
                        groupRepository.save(group);
                    }



                }catch (ParserException e){
                    return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()),HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
            }

        }else
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
    }

}

