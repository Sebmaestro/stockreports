import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Test from './components/Old'
import Login from './components/Login'
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import Register from './components/Register'
import TestGetUsers from './components/TestGetUsers'
import Dashboard from './components/Dashboard'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <nav>
        <Link to="/">Login</Link>
        <br />
        <Link to="/register">Register</Link>
        <br />
        <Link to="/test">Test</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/test" element={<TestGetUsers />} />
        <Route path="/dashboard" element={<Dashboard />} />        
      </Routes>
    </>
  )
}

export default App
