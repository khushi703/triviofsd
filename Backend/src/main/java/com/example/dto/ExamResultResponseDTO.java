package com.example.dto;

public class ExamResultResponseDTO {
    private int totalScore;
    private int correctAnswers;
    private int incorrectAnswers;
    private Double totalTimeSpent;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ExamResultResponseDTO(int totalScore, int correctAnswers, int incorrectAnswers, Double totalTimeSpent, String username) {
        this.totalScore = totalScore;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.totalTimeSpent = totalTimeSpent;
        this.username=username;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public Double getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Double totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

}
