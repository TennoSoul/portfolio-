import React, { useEffect, useState } from "react";

function App() {
  const [projects, setProjects] = useState([]);
  const [skills, setSkills] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/projects")
      .then(res => res.json())
      .then(data => setProjects(data.projects || []));

    fetch("http://localhost:8080/api/skills")
      .then(res => res.json())
      .then(data => setSkills(data.skills || []));

    fetch("http://localhost:8081/api/games")
      .then(res => res.json())
      .then(data => setGames(data))
      .catch(err => console.error("Error fetching games:", err));
  }, []);

  return (
    <div style={{ padding: "2rem", fontFamily: "sans-serif" }}>
      <h1>My Portfolio</h1>

      <h2>Projects</h2>
      <ul>
        {projects.map(p => (
          <li key={p.id}>
            <strong>{p.title}</strong> — {p.description}
          </li>
        ))}
      </ul>

      <h2>Skills</h2>
      <ul>
        {skills.map((s, i) => (
          <li key={i}>{s}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
