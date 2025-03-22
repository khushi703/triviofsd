import React from "react";
import { useNavigate } from "react-router-dom"; // ✅ Import useNavigate
import { Book, CheckCircle, Trophy } from "lucide-react";
import "../css/studentcard.css";

export function ExamCard({ id, name, author, subjects, difficulty, totalquestions }) {
  const navigate = useNavigate(); // ✅ Initialize navigation

  const handleStartExam = () => {
    navigate(`/exam/${id}`); // ✅ Navigate to ExamInterface with exam ID
  };

  return (
    <div className="subject-card" onClick={handleStartExam} style={{ cursor: "pointer" }}>
      <div className="subject-card-header">
        <h3 className="subject-card-title">
          {/*<Book size={20} />*/}
          {name}
        </h3>
      </div>
      <div className="subject-card-content">
        <div className="subject-card-info">
          <div className="info-item">
            {/*<Trophy size={20} color="#EAB308" />*/}
            <span className="info-text">By: {author}</span>
          </div>
          <div className="info-item">
            {/*<CheckCircle size={20} color="#22C55E" />*/}
            <span className="info-text">Level: {difficulty}</span>
          </div>
          <div className="info-item">
            {/*<CheckCircle size={20} color="#22C55E" />*/}
            <span className="info-text">Questions: {totalquestions}</span>
          </div>
        </div>
      </div>
      <div className="subject-card-footer">
        <div className="exam-tags">
          {subjects.map((exam, index) => (
            <span key={index} className="exam-tag">
              {exam}
            </span>
          ))}
        </div>
      </div>
    </div>
  );
}
