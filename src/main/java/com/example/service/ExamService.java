package com.example.service;

import com.example.model.Exam;
import com.example.model.Option;
import com.example.model.Question;
import com.example.repository.ExamRepo;
import com.example.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepo examRepository;

    @Autowired
    private QuestionRepo questionRepository;

    @Transactional
    public Exam saveExam(Exam exam) {
        if (exam.getQuestions() != null) {
            for (Question question : exam.getQuestions()) {
                question.setExam(exam);

                if (question.getOptions() == null || question.getOptions().size() != 4) {
                    throw new IllegalArgumentException("Each question must have exactly 4 options.");
                }

                Option correctOption = question.getOptions().stream()
                        .filter(Option::isCorrect)
                        .findFirst()
                        .orElse(null);

                if (correctOption == null) {
                    throw new IllegalArgumentException("Each question must have one correct option.");
                }

                question.setCorrectOption(correctOption);

                for (Option option : question.getOptions()) {
                    option.setQuestion(question);
                }
            }
        }

        Exam savedExam = examRepository.save(exam);
        questionRepository.saveAll(exam.getQuestions()); // ✅ Explicitly save questions
        return savedExam;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Transactional
    public Exam getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        // Manually load subjects before returning
        exam.getSubjects().size();  // Forces Hibernate to initialize subjects

        return exam;
    }
    public List<Exam> getExamsByAuthor(String author) {
        return examRepository.findByAuthor(author); // Fetch exams only for the logged-in user
    }
    public List<Question> getQuestionsByExamId(Long examId) {
        List<Question> questions = questionRepository.findByExamId(examId);
        questions.forEach(question -> {
            // Optionally, clean up any unnecessary fields or modify the structure as needed.
            question.setExam(null); // Remove the exam object from each question to avoid repetition
        });
        return questions;
    }
}