// Event array
let eventList = [
    { name: "Workshop on Baking", date: "2025-06-01", category: "Food" },
    { name: "Local Band Night", date: "2025-06-15", category: "Music" },
    { name: "Jazz Evening", date: "2025-07-01", category: "Music" }
  ];
  
  // Function to display events
  function displayEvents(events) {
    const container = document.getElementById("event-list");
    container.innerHTML = "";
  
    const cards = events.map(event => {
      return `<div style="border:1px solid #ccc; margin:10px; padding:10px;">
                <h3>${event.name}</h3>
                <p>Date: ${event.date}</p>
                <p>Category: ${event.category}</p>
              </div>`;
    });
  
    container.innerHTML = cards.join("");
  }
  
  // Add a new event using .push()
  function addNewEvent() {
    const newEvent = { name: "Workshop on Pottery", date: "2025-08-10", category: "Art" };
    eventList.push(newEvent);
    alert("New event added!");
    displayEvents(eventList);
  }
  
  // Filter only music events using .filter()
  function showMusicEvents() {
    const musicEvents = eventList.filter(event => event.category === "Music");
    displayEvents(musicEvents);
  }
  
  // Initial display
  window.onload = function () {
    displayEvents(eventList);
  };
  