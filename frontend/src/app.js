// frontend/src/App.js
import React, { useState, useEffect } from 'react';
import Projects from './components/Projects';
import Minigames from './components/Minigames';
import './App.css';

function App() {
  const [projects, setProjects] = useState([]);
  const [games, setGames] = useState([]);

  useEffect(() => {
    fetchProjects();
    fetchGames();
  }, []);

  const fetchProjects = async () => {
    const res = await fetch('http://localhost:8080/api/projects');
    const data = await res.json();
    setProjects(data.projects);
  };

  return (
    <div className="App">
      <header>
        <h1>Your Name - Full Stack Developer</h1>
        <p>Go | React | Java</p>
      </header>
      
      <Projects projects={projects} />
      <Minigames games={games} />
    </div>
  );
}