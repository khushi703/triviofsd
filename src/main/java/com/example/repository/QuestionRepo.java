package com.example.repository;

import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    List<Question> findByExamId(Long examId);
}
