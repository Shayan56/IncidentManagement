const apiBaseUrl = "http://localhost:8080/api"; // Replace with your backend URL
const authToken = localStorage.getItem("token");

// Redirect if not authenticated
if (!authToken && window.location.pathname === "/dashboard.html") {
  window.location.href = "/index.html";
}

// API Helper
async function apiRequest(url, method, body) {
  const headers = {
    "Content-Type": "application/json",
  };
  if (authToken) headers["Authorization"] = `Bearer ${authToken}`;

  const response = await fetch(apiBaseUrl + url, {
    method,
    headers,
    body: JSON.stringify(body),
  });

  return response.json();
}

// Logout
function logout() {
  localStorage.removeItem("token");
  window.location.href = "/index.html";
}
