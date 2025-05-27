let events = []; // Scoped event list

// 1. Function to add a new event
function addEvent(name, date, seats, category) {
  events.push({ name, date, seats, category });
}

// 2. Closure to track total registrations per category
function createCategoryTracker() {
  const counts = {};
  return function(category) {
    if (!counts[category]) counts[category] = 0;
    counts[category]++;
    console.log(`Registrations for ${category}: ${counts[category]}`);
  };
}

const trackRegistration = createCategoryTracker();

// 3. Register user with scoped event access
function registerUser(index) {
  try {
    const event = events[index];
    if (!event) throw new Error("Event not found");
    if (event.seats <= 0) throw new Error("No seats available");

    event.seats--;
    alert(`Registered for ${event.name}`);
    trackRegistration(event.category);
    displayEvents();
  } catch (err) {
    alert("Registration failed: " + err.message);
  }
}

// 4. Higher-order function to filter by dynamic condition
function filterEvents(callback) {
  return events.filter(callback);
}

// 5. Filter by category using higher-order function
function filterEventsByCategory(category) {
  return filterEvents(event => event.category.toLowerCase() === category.toLowerCase());
}

// Search handler
function searchByCategory() {
  const category = document.getElementById("search-category").value;
  const filtered = filterEventsByCategory(category);
  displayEvents(filtered);
}

// Display event list
function displayEvents(list = events) {
  const container = document.getElementById("event-list");
  container.innerHTML = "";

  list.forEach((event, index) => {
    const div = document.createElement("div");
    div.innerHTML = `
      <h3>${event.name}</h3>
      <p>Date: ${event.date}</p>
      <p>Seats: ${event.seats}</p>
      <p>Category: ${event.category}</p>
      <button onclick="registerUser(${index})">Register</button>
      <hr>
    `;
    container.appendChild(div);
  });
}

// Add some sample events
function addSampleEvents() {
  addEvent("Farmers Market", "2025-06-10", 10, "Food");
  addEvent("Yoga Class", "2025-06-15", 5, "Health");
  addEvent("Tech Meetup", "2025-07-01", 15, "Technology");
  addEvent("Art Fair", "2025-06-20", 0, "Culture");
  displayEvents();
}
