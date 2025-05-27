// Event class definition
class Event {
    constructor(name, date, seats, category) {
      this.name = name;
      this.date = date;
      this.seats = seats;
      this.category = category;
    }
  
    // Method to register a user
    register() {
      if (this.checkAvailability()) {
        this.seats--;
        alert(`Registered for ${this.name}`);
      } else {
        alert(`${this.name} is full.`);
      }
    }
  }
  
  // Prototype method to check seat availability
  Event.prototype.checkAvailability = function () {
    return this.seats > 0;
  };
  
  // List of event objects
  let eventObjects = [];
  
  // Display events using Object.entries()
  function displayEventObjects() {
    const container = document.getElementById("event-list");
    container.innerHTML = "";
  
    eventObjects.forEach((event, index) => {
      const entries = Object.entries(event)
        .map(([key, val]) => `<p><strong>${key}</strong>: ${val}</p>`)
        .join("");
  
      container.innerHTML += `
        <div>
          <h3>${event.name}</h3>
          ${entries}
          <p><strong>Available?</strong> ${event.checkAvailability() ? "Yes" : "No"}</p>
          <button onclick="eventObjects[${index}].register(); displayEventObjects();">Register</button>
          <hr>
        </div>
      `;
    });
  }
  
  // Create sample events
  function createSampleEvents() {
    eventObjects = [
      new Event("Farmers Market", "2025-06-10", 10, "Food"),
      new Event("Yoga Class", "2025-06-15", 0, "Health"),
      new Event("Tech Meetup", "2025-07-01", 5, "Technology")
    ];
    displayEventObjects();
  }
  