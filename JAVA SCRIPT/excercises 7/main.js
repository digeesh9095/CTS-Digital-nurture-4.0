// Sample event list
let events = [
    { name: "Cooking Class", date: "2025-06-05", seats: 5 },
    { name: "Rock Concert", date: "2025-06-20", seats: 0 },
    { name: "Art Workshop", date: "2025-07-10", seats: 3 }
  ];
  
  // Display events dynamically
  function renderEvents() {
    const container = document.querySelector("#event-container");
    container.innerHTML = ""; // Clear previous
  
    events.forEach((event, index) => {
      const card = document.createElement("div");
      card.style.border = "1px solid #ccc";
      card.style.padding = "10px";
      card.style.margin = "10px";
  
      const name = document.createElement("h3");
      name.textContent = event.name;
  
      const date = document.createElement("p");
      date.textContent = `Date: ${event.date}`;
  
      const seats = document.createElement("p");
      seats.textContent = `Seats: ${event.seats}`;
  
      const regBtn = document.createElement("button");
      regBtn.textContent = "Register";
      regBtn.disabled = event.seats === 0;
      regBtn.onclick = () => {
        if (events[index].seats > 0) {
          events[index].seats--;
          renderEvents(); // UI update
        }
      };
  
      const cancelBtn = document.createElement("button");
      cancelBtn.textContent = "Cancel";
      cancelBtn.style.marginLeft = "10px";
      cancelBtn.onclick = () => {
        events[index].seats++;
        renderEvents(); // UI update
      };
  
      card.append(name, date, seats, regBtn, cancelBtn);
      container.appendChild(card);
    });
  }
  
  // Initial load
  window.onload = renderEvents;
  