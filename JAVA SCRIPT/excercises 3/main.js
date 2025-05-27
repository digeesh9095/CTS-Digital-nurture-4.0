// Sample event data
const events = [
    { name: "Local Farmers Market", date: "2025-06-10", seats: 5 },
    { name: "Community Yoga", date: "2025-05-20", seats: 0 },
    { name: "Music Fest", date: "2025-07-01", seats: 10 },
    { name: "Tech Talk", date: "2024-12-10", seats: 15 } // Past event
  ];
  
  // Utility function to check if event is upcoming
  function isUpcoming(dateStr) {
    const today = new Date();
    const eventDate = new Date(dateStr);
    return eventDate >= today;
  }
  
  // Display valid events
  function displayEvents() {
    const container = document.getElementById("event-list");
    container.innerHTML = "";
  
    events.forEach((event, index) => {
      if (isUpcoming(event.date) && event.seats > 0) {
        const div = document.createElement("div");
        div.innerHTML = `
          <h3>${event.name}</h3>
          <p>Date: ${event.date}</p>
          <p>Available Seats: ${event.seats}</p>
          <button onclick="register(${index})">Register</button>
          <hr>
        `;
        container.appendChild(div);
      }
    });
  }
  
  // Handle registration with try-catch
  function register(index) {
    try {
      if (!events[index]) throw new Error("Event not found");
      if (events[index].seats <= 0) throw new Error("No seats available");
  
      events[index].seats--;
      alert(`Registered for ${events[index].name}`);
      displayEvents(); // Refresh list
    } catch (err) {
      alert("Registration failed: " + err.message);
    }
  }
  
  // Run on page load
  window.onload = function () {
    alert("Page is fully loaded!");
    displayEvents();
  };
  