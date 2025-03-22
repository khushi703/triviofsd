package com.example.repository;

import com.example.model.Exam;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {

    @EntityGraph(attributePaths = {"questions", "subjects"}) // Load both collections eagerly
    Optional<Exam> findById(Long id);
    List<Exam> findByAuthor(String author); // Query exams by author
}

