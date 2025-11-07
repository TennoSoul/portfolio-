import React from 'react';
import useFetch from '../../hooks/useFetch';
import '../../App.css';
import './Projects.css';

export default function Projects() {
  const { data: projects, loading, error } = useFetch('http://localhost:8080/api/projects');

  if (loading) return <p className="loading">Loading projects...</p>;
  if (error) return <p className="error">Error: {error}</p>;

  return (
    <div className="projects-container">
      <h2>Projects</h2>
      <div className="projects-grid">
        {projects.map((p) => (
          <div className="project-card" key={p.id}>
            <img src={p.image} alt={p.title} />
            <h3>{p.title}</h3>
            <p>{p.description}</p>
            <a href={p.link} target="_blank" rel="noreferrer">View</a>
          </div>
        ))}
      </div>
    </div>
  );
}
