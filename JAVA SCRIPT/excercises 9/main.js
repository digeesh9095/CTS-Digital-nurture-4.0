const API_URL = "https://mocki.io/v1/89dca239-1faf-4e30-a5d9-900f8a146b15"; // replace with a real/working mock API

// Show events in DOM
function displayEvents(events) {
  const container = document.querySelector("#event-container");
  container.innerHTML = "";
  events.forEach(event => {
    const div = document.createElement("div");
    div.style.border = "1px solid #ccc";
    div.style.margin = "10px";
    div.style.padding = "10px";
    div.innerHTML = `
      <h3>${event.name}</h3>
      <p>Date: ${event.date}</p>
      <p>Category: ${event.category}</p>
    `;
    container.appendChild(div);
  });
}

// Show/hide loading spinner
function toggleSpinner(show) {
  document.querySelector("#spinner").style.display = show ? "block" : "none";
}

//
// 🔁 Version 1: Using .then() and .catch()
//

function loadEventsWithThen() {
  toggleSpinner(true);
  fetch(API_URL)
    .then(response => response.json())
    .then(data => {
      displayEvents(data.events || []);
      toggleSpinner(false);
    })
    .catch(error => {
      console.error("Error fetching events:", error);
      toggleSpinner(false);
    });
}

//
// ⚡ Version 2: Using async/await
//

async function loadEventsAsync() {
  toggleSpinner(true);
  try {
    const response = await fetch(API_URL);
    const data = await response.json();
    displayEvents(data.events || []);
  } catch (error) {
    console.error("Async error:", error);
  } finally {
    toggleSpinner(false);
  }
}
