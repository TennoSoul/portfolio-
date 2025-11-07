import React from 'react';
import useFetch from '../../hooks/useFetch';
import './Minigames.css';

export default function Minigames() {
  const { data: games, loading, error } = useFetch('http://localhost:8080/api/games');

  if (loading) return <p className="loading">Loading games...</p>;
  if (error) return <p className="error">Error: {error}</p>;

  return (
    <div className="minigames-container">
      <h2>Mini Games</h2>
      <div className="minigames-grid">
        {games.map((g) => (
          <div className="minigame-card" key={g.id}>
            <h3>{g.title}</h3>
            <p>{g.description}</p>
            <a href={g.link} target="_blank" rel="noreferrer">Play</a>
          </div>
        ))}
      </div>
    </div>
  );
}
