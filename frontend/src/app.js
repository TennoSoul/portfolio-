// frontend/src/App.js
import React, { useState, useEffect } from 'react';
import MemoryGame from './components/MemoryGame';
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

  const fetchGames = async () => {
    try {
      const res = await fetch('http://localhost:8080/api/games');
      const data = await res.json();
      setGames(data.games || []);
    } catch (err) {
      console.error('fetchGames error', err);
      setGames([]);
    }
  };

  return (
    <div className="App">
      <header>
        <h1>Your Name - Full Stack Developer</h1>
        <p>Go | React | Java</p>
      </header>
      
      <section>
        <h2>Projects</h2>
        <ul>{projects.map(p => <li key={p.id}>{p.title}</li>)}</ul>
      </section>
      <section>
        <h2>Games</h2>
        <ul>{games.map(g => <li key={g.id}>{g.name}</li>)}</ul>
      </section>
      <MemoryGame />
    </div>
  );
}

export default App;