let events = [
    { name: "Rock Concert", date: "2025-06-10", category: "Music", seats: 5 },
    { name: "Art Workshop", date: "2025-06-12", category: "Art", seats: 2 },
    { name: "Yoga Class", date: "2025-06-15", category: "Health", seats: 0 }
  ];
  
  // Display events
  function renderEvents(filteredEvents = events) {
    const container = document.querySelector("#event-container");
    container.innerHTML = "";
  
    filteredEvents.forEach((event, index) => {
      const card = document.createElement("div");
      card.style.border = "1px solid #ccc";
      card.style.margin = "10px";
      card.style.padding = "10px";
  
      card.innerHTML = `
        <h3>${event.name}</h3>
        <p>Date: ${event.date}</p>
        <p>Category: ${event.category}</p>
        <p>Seats: ${event.seats}</p>
      `;
  
      const regBtn = document.createElement("button");
      regBtn.textContent = "Register";
      regBtn.disabled = event.seats === 0;
      regBtn.onclick = () => {
        if (events[index].seats > 0) {
          events[index].seats--;
          renderEvents(filteredEvents);
        }
      };
  
      card.appendChild(regBtn);
      container.appendChild(card);
    });
  }
  
  // Filter by category (onchange)
  document.querySelector("#category-filter").onchange = function () {
    const selected = this.value;
    if (selected === "all") {
      renderEvents();
    } else {
      const filtered = events.filter(e => e.category === selected);
      renderEvents(filtered);
    }
  };
  
  // Search by name (keydown)
  document.querySelector("#search-input").addEventListener("keydown", function (e) {
    const term = e.target.value.toLowerCase();
    const filtered = events.filter(e => e.name.toLowerCase().includes(term));
    renderEvents(filtered);
  });
  
  // Initial render
  window.onload = () => renderEvents();
  