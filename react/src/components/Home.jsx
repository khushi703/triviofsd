import React, { useState, useEffect } from "react";
import "../css/home.css";
import HeroImage from "../assets/Trivio.png"; // Replace with your 3D character image
import { useNavigate } from "react-router-dom";

const Home = () => {
    const text = "Learning Made Easy";
    const [displayText, setDisplayText] = useState("L"); // Start with "L"
    const [index, setIndex] = useState(1); // Start from 1 (so "L" stays)
    const [isDeleting, setIsDeleting] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        let timeout;
        if (!isDeleting && index < text.length) {
            // Typing effect
            timeout = setTimeout(() => {
                setDisplayText((prev) => prev + text[index]);
                setIndex(index + 1);
            }, 150);
        } else if (isDeleting && index > 1) {
            // Deleting effect but never remove "L"
            timeout = setTimeout(() => {
                setDisplayText((prev) => prev.slice(0, -1));
                setIndex(index - 1);
            }, 100);
        } else if (index === text.length && !isDeleting) {
            timeout = setTimeout(() => setIsDeleting(true), 1000);
        } else if (index === 1 && isDeleting) {
            // Reset but keep "L"
            setIsDeleting(false);
        }
        return () => clearTimeout(timeout);
    }, [index, isDeleting, text]);

    return (
        <div>
            {/* Navigation Bar */}
            <nav className="navbar">
                <div className="logo">Trivio</div>
                <div className="nav-links">
                    <a href="#" onClick={() => navigate("/register")}>Sign Up</a>
                    <a href="#" onClick={() => navigate("/login")}>Login</a>

                </div>
            </nav>

            {/* Main Content */}
            <div className="hero-container">
                <div className="hero-content">
                    {/* Left Side: TRIVIO with Tagline and Sign Up Button */}
                    <div className="hero-text">
                        <h1>Trivio</h1>
                        <p id="hero-para">Unlock Knowledge, One Question at a Time!</p>
                        <div className="btn-try">
                            <button id="try-it" onClick={() => navigate("/register")}>Sign Up</button>
                        </div>
                    </div>

                    {/* Right Side: 3D Character */}
                    <div className="hero-image">
                        <img src={HeroImage} alt="3D Character" />
                    </div>
                </div>
            </div>

            {/* Footer */}
            <footer className="footer">
                <p>Created by @khushiZalavadiya & @omunadkat</p>
            </footer>
        </div>
    );
};

export default Home;