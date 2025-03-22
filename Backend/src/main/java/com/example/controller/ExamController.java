package com.example.controller;

import com.example.dto.ExamResultResponseDTO;
import com.example.dto.ExamSubmissionRequestDTO;
import com.example.model.Exam;
import com.example.model.Question;
import com.example.model.Submission;
import com.example.repository.SubmissionRepo;
import com.example.service.ExamService;
import com.example.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Exam> getUserExams(@AuthenticationPrincipal UserDetails userDetails) {
        String loggedInUserEmail = userDetails.getUsername(); // Get the logged-in user's email
        return examService.getExamsByAuthor(loggedInUserEmail); // Fetch exams for this user
    }


    @PostMapping("/{examId}/submit")
    public ResponseEntity<?> submitExam(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long examId,
            @RequestBody ExamSubmissionRequestDTO request) {

        // Log the incoming request for debugging
        System.out.println("Received submission request: " + request);

        Submission submission = submissionService.submitExam(
                userDetails.getUsername(), examId, request.getAnswers(), request.getTotalTimeSpent());

        return ResponseEntity.ok(submission);
    }

    @GetMapping("/{submissionId}/result")
    public ResponseEntity<ExamResultResponseDTO> getExamResult(@PathVariable Long submissionId) {
        Submission submission = submissionService.getSubmissionById(submissionId);

        ExamResultResponseDTO response = new ExamResultResponseDTO(
                submission.getTotalScore(),
                submission.getCorrectAnswers(),
                submission.getIncorrectAnswers(),
                submission.getTotalTimeSpent(),
                submission.getStudent().getUsername()
        );

        return ResponseEntity.ok(response);
    }
}