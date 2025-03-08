package com.examease.sdp.controller;

import com.examease.sdp.DTO.ExamResultResponse;
import com.examease.sdp.DTO.ExamSubmissionRequest;
import com.examease.sdp.model.*;
import com.examease.sdp.repository.SubmissionRepo;
import com.examease.sdp.service.ExamService;
import com.examease.sdp.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamController {

    private final ExamService examService;
    @Autowired
    private SubmissionService submissionService;
    private SubmissionRepo submissionRepo;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/create")
    public Exam createExam(@RequestBody Exam exam, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            exam.setAuthor(userDetails.getUsername()); // ✅ Set the author from JWT
        } else {
            throw new RuntimeException("User not authenticated");
        }

        return examService.saveExam(exam);
    }

    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id) {
        return examService.getExamById(id);
    }

    @GetMapping("/{examId}/questions")
    public List<Question> getQuestions(@PathVariable Long examId) {
        return examService.getQuestionsByExamId(examId);
    }

    @GetMapping("/get")
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }
    @PostMapping("/{examId}/submit")
    public ResponseEntity<?> submitExam(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long examId,
            @RequestBody ExamSubmissionRequest request) {

        Submission submission = submissionService.submitExam(
                userDetails.getUsername(), examId, request.getAnswers(), request.getTotalTimeSpent());

        return ResponseEntity.ok(submission);
    }
    @GetMapping("/{submissionId}/result")
    public ResponseEntity<ExamResultResponse> getExamResult(@PathVariable Long submissionId) {
        Submission submission = submissionService.getSubmissionById(submissionId);

        ExamResultResponse response = new ExamResultResponse(
                submission.getTotalScore(),
                submission.getCorrectAnswers(),
                submission.getIncorrectAnswers(),
                submission.getTotalTimeSpent(),
                submission.getStudent().getUsername()
        );

        return ResponseEntity.ok(response);
    }
}
