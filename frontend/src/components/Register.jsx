import { useState } from "react";   

function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");


  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json", 
        },
        body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
          const user = await response.json();
          console.log("User created:", user);
          
          setMessage(`User created succesfully, ${user.username}`);
        } else if (response.status === 409) {
          console.log("Username already exists");
          setMessage("Username already exists");
        } else if (response.status === 400) {
          console.log("Bad req");
          setMessage("Bad req");
        } else {
          console.log("Något annat gick fel");
          setMessage("Något annat gick fel");
        }
    } catch (error) {
      console.error("Registration failed:", error);
        setMessage("Något gick fel");
    }
  };

  return (
    <>
      <h1>Register</h1>
      <form onSubmit={handleRegister}>
        <input 
        type="text" 
        placeholder="Användarnamn"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        />
        <br />
        <input 
        type="text" 
        placeholder="Lösenord"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <button type="submit">Register</button>
        <p>{message}</p>
      </form>
    </>
  );
}

export default Register;
