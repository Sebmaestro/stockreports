import React from "react";

function TestGetUsers() {
  const [users, setUsers] = React.useState([]);
  const [message, setMessage] = React.useState("massagee");

  const fetchUsers = async (e) => {
    try {
      const response = await fetch("http://localhost:8080/api/auth/getAll", {
        method: "GET",
      });
      if (response.ok) {
        console.log("Response ok");
        const data = await response.json();
        console.log(data);
        console.log(JSON.stringify(data));
        setUsers(data);

        setMessage("Users fetched successfully");
      } else {
        //Kan ej hända
        console.error("Failed to fetch users");
        setMessage("Failed to fetch users");
      }
    } catch (error) {
      console.error(error.message);
      setMessage("Error fetching users");
    }
  };

  return (
    <>
      <h1>Users</h1>
      <button onClick={fetchUsers}>Fetch da users</button>
      <ul>
        {users.map((user) => (
          <li key={user.id}>{user.username}</li>
        ))}
      </ul>
      <p>{message}</p>
    </>
  );
}

export default TestGetUsers;
