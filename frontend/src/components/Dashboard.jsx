import React from "react";

function Dashboard() {
  const [showInput, setShowInput] = React.useState(false);
  const [ticker, setTicker] = React.useState("");
  const [message, setMessage] = React.useState("ett message");

    const handleAddInputButton = () => {
        setShowInput(true);
    }

    const handleAddCompanyButton = async (e) => {
        e.preventDefault();
        setTicker(e.target.value);
        try {
            //const response = await fetch("http://localhost:8080/api/stock/test", {
            const response = await fetch("http://localhost:8080/api/stock/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ ticker }),
            });
            if (response.ok) {
                console.log("Company added successfully");
                const ticker = await response.text();
                setMessage(ticker);
                console.log(ticker);
                
                /* const ticker = response.text();
                console.log(ticker); */
            } else {
                console.log("Failed to add company");
            }
            
            
            //setTicker(response.json);
        } catch (error) {
            console.log("http error:", error);
        }
    }

  return (
    <>
      <h1>Dashboard</h1>
      <p>Welcome to the dashboard!</p>
      <p>This is a protected route.</p>

      <button onClick={handleAddInputButton}>Add companies</button>
      <button type="button">Get all reports</button>
      <br />

      {showInput && (
        <>
        <input 
        type="text" 
        value={ticker}
        onChange={(e) => setTicker(e.target.value)}
        placeholder="Ticker"
        />
        <button onClick={handleAddCompanyButton}>add</button>
        <p>{ticker}</p>
        
        </>
        )}
        <p>{message}</p>
    </>
  );
}

export default Dashboard;
