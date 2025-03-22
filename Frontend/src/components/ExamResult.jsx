import { useEffect, useState } from "react";
import { useParams, useLocation, useNavigate } from "react-router-dom";
import axiosInstance from "../axiosConfig";
import "../css/ExamResults.css";

const ExamResult = () => {
    const { submissionId } = useParams();
    const location = useLocation();
    const navigate = useNavigate();
    const [result, setResult] = useState(location.state?.result || null);
    const [isLoading, setIsLoading] = useState(!location.state?.result);

    useEffect(() => {
        console.log("Initial result from location.state:", location.state?.result);
        if (!result) {
            setIsLoading(true);
            axiosInstance.get(`/api/exams/${submissionId}/result`)
                .then(response => {
                    console.log("Fetched result from API:", response.data);
                    setResult(response.data);
                    setIsLoading(false);
                })
                .catch(error => {
                    console.error("Error fetching exam result:", error);
                    setIsLoading(false);
                });
        }
    }, [submissionId, result, location.state]);

    // Calculate percentages for visual display
    const getScorePercentage = () => {
        if (!result) return 0;
        const total = result.correctAnswers + result.incorrectAnswers;
        return total > 0 ? Math.round((result.correctAnswers / total) * 100) : 0;
    };

    // Format time in a readable way
    const formatTime = (seconds) => {
        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = seconds % 60;
        return `${minutes}m ${remainingSeconds}s`;
    };

    if (isLoading) {
        return (
            <div className="loading-container">
                <div className="loading-text">Loading your results...</div>
            </div>
        );
    }

    if (!result) return <p>Loading result...</p>;

    const scorePercentage = getScorePercentage();
    const scoreColorClass = scorePercentage >= 70
        ? "score-high"
        : scorePercentage >= 50
            ? "score-medium"
            : "score-low";

    return (
        <div className="page-container">
            <div className="result-container">
                <div className="trophy-container">
                    <div className="trophy-circle"></div>
                    <div
                        className={`trophy-progress ${scoreColorClass}`}
                        style={{
                            transform: `rotate(${scorePercentage * 3.6 - 45}deg)`
                        }}
                    ></div>
                    <div className="trophy-icon">🏆</div>
                </div>

                <h1 className="result-title">
                    <span className="icon">📊</span>
                    Quiz Result
                </h1>

                <div className="score-card">
                    <div className="total-score">
                        <div className={`score-value ${scoreColorClass}`}>
                            {result.totalScore}
                        </div>
                        <div className="score-label">TOTAL SCORE</div>
                    </div>

                    <div className="progress-bar-container">
                        <div
                            className={`progress-bar ${scoreColorClass}`}
                            style={{ width: `${scorePercentage}%` }}
                        ></div>
                    </div>

                    <div className="stats-grid">
                        <div className="stat-card">
                            <div className="stat-header">
                                <span className="icon correct-icon">✓</span>
                                <span className="stat-label">Correct</span>
                            </div>
                            <div className="stat-value correct-value">
                                {result.correctAnswers}
                            </div>
                        </div>

                        <div className="stat-card">
                            <div className="stat-header">
                                <span className="icon wrong-icon">✗</span>
                                <span className="stat-label">Wrong</span>
                            </div>
                            <div className="stat-value wrong-value">
                                {result.incorrectAnswers}
                            </div>
                        </div>
                    </div>
                </div>

                <div className="time-card">
                    <span className="icon time-icon">⏱️</span>
                    <span className="time-label">Time Taken:</span>
                    <span className="time-value">{formatTime(result.totalTimeSpent)}</span>
                </div>

                <button
                    onClick={() => navigate("/")}
                    className="home-button"
                >
                    <span className="icon"></span>
                    Go to Home
                </button>
            </div>
        </div>
    );
};

export default ExamResult;
