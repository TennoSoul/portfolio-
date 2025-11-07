
import React from 'react';
import './Header.css';

export default function Header() {
  return (
    <header className="header">
      <h1>Your Name</h1>
      <p>Full Stack Developer — Go | React | Java</p>
      <nav>
        <a href="#projects">Projects</a>
        <a href="#minigames">Mini Games</a>
      </nav>
    </header>
  );
}
