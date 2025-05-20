import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      console.log("Hello :)");
      console.log(username, password);
      console.log(JSON.stringify({ username, password }));
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const user = await response.json();
        console.log("Går vi in här");        
        setMessage(`Välkommen, ${user.username}`);

        navigate("/dashboard");
        
      } else if (response.status === 401) {
        console.log("Wrong password");
        setMessage("Wrong password");
        //fixa här fixaaa
      } else if (response.status === 404) {
        console.log("User not found");
        setMessage("User not found");
      } else {
        console.log("Något annat gick fel");
        setMessage("Något annat gick fel");
      }
    } catch (error) {
      console.error("Login failed:", error);
      setMessage("Något gick fel");
    }
  };

  return (
    <>
      <form onSubmit={handleLogin}>
        <h2>Logga in</h2>
        <input
          type="text"
          placeholder="Användarnamn"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <br />
        <input
          type="password"
          placeholder="Lösenord"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <button type="submit">Logga in</button>

        <p>{message}</p>
      </form>
      <button type="button" onClick={() => navigate("/register")}>
        Registrera
      </button>
    </>
  );
}

export default Login;
