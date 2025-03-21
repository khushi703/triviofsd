import "../css/header.css"
// import ProfileImage from "../assets/loginphoto.avif";
import React from "react";

export default function Header() {
  return (
    <header className="app-header">
      <div className="header-left">
        <h1 className="header-title">Trivio</h1>
      </div>
      <div className="header-right">
        {/*<div className="header-search">*/}
        {/*  <input type="search" placeholder="Search..." className="search-input" />*/}
        {/*</div>*/}
        <div className="header-actions">
          <button className="header-button">
          </button>
            <div className="user-profile">
                {/*<img src={ProfileImage} alt="Profile"/>*/}
            </div>
        </div>
      </div>
    </header>
  )
}

