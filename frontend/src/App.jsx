import React from 'react';
import Header from './components/Header';
import Projects from './components/Projects/Projects';
import Minigames from './components/Minigames/Minigames';
import './App.css';

export default function App() {
  return (
    <div className="app">
      <Header />
      <main>
        <section id="projects">
          <Projects />
        </section>
        <section id="minigames">
          <Minigames />
        </section>
      </main>
      <footer>
        <p>© {new Date().getFullYear()} Your Name — Built with React</p>
      </footer>
    </div>
  );
}
    