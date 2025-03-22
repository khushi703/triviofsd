package com.example.dto;

import java.util.List;

public class ExamSubmissionRequestDTO {
    private List<StudentAnswerRequestDTO> answers;
    private Double totalTimeSpent;

    public List<StudentAnswerRequestDTO> getAnswers() { return answers; }
    public void setAnswers(List<StudentAnswerRequestDTO> answers) { this.answers = answers; }

    public Double getTotalTimeSpent() { return totalTimeSpent; }
    public void setTotalTimeSpent(Double totalTimeSpent) { this.totalTimeSpent = totalTimeSpent; }
}

