import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import MemoryGame from "./components/MemoryGame";

function Home() {
  return (
    <div>
      <h2>Welcome to Portfolio Games</h2>
      <p>Select a game from the navigation above.</p>
    </div>
  );
}
  
function TicTacToe() {
  return (
    <div>
      <h2>Tic Tac Toe</h2>
      <p>Coming soon!</p>
    </div>
  );
}

function App() {
  return (
    <Router>
      <header style={{ padding: "1rem", background: "#eee" }}>
        <h1>Portfolio Games</h1>
        <nav>
          <Link to="/" style={{ marginRight: "1rem" }}>Home</Link>
          <Link to="/memory">Memory Game</Link>
          <Link to="/tictactoe" style={{ marginLeft: "1rem" }}>Tic Tac Toe</Link>
        </nav>
      </header>
      <div style={{ padding: "2rem" }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/memory" element={<MemoryGame />} />
          <Route path="/tictactoe" element={<TicTacToe />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;