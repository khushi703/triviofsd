import { useEffect, useState } from "react";
import { useParams, useLocation, useNavigate } from "react-router-dom";
import axiosInstance from "../axiosConfig";
const ExamResult = () => {
  const { submissionId } = useParams();
  const location = useLocation();
  const navigate = useNavigate();
  const [result, setResult] = useState(location.state?.result || null);

  useEffect(() => {
    console.log("Initial result from location.state:", location.state?.result);
    if (!result) {
      axiosInstance.get(`/api/exams/${submissionId}/result`)
          .then(response => {
            console.log("Fetched result from API:", response.data);
            setResult(response.data);
          })
          .catch(error => console.error("Error fetching exam result:", error));
    }
  }, [submissionId, result, location.state]);

  if (!result) return <p>Loading result...</p>;

  return (
      <div>
        <h1>Exam Result</h1>
        <p>Score: {result.totalScore}</p>
        <p>Correct Answers: {result.correctAnswers}</p>
        <p>Wrong Answers: {result.incorrectAnswers}</p>
        <p>Total Time Taken: {result.totalTimeSpent} seconds</p>
        <button onClick={() => navigate("/")}>Go to Home</button>
      </div>
  );
};

export default ExamResult;
