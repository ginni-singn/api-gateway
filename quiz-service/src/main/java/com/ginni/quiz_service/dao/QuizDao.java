package com.ginni.quiz_service.dao;


import com.ginni.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
//2 param in jparepo<type o table i m wrking,primary key data type>
public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
