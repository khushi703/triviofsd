package com.examease.sdp.repository;

import com.examease.sdp.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswerRepo extends JpaRepository<StudentAnswer, Long> {
}