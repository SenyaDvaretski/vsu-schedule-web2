package com.vsuscheduleweb.repositories;

import com.vsuscheduleweb.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {

}