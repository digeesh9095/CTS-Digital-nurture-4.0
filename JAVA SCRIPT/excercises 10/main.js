// Initial events data
const events = [
    { name: "Rock Concert", date: "2025-06-10", category: "Music", seats: 5 },
    { name: "Art Workshop", date: "2025-06-12", category: "Art", seats: 2 },
    { name: "Yoga Class", date: "2025-06-15", category: "Health", seats: 0 }
  ];
  
  // Render events to DOM
  const renderEvents = (eventList = events) => {
    const container = document.querySelector("#event-container");
    container.innerHTML = "";
  
    eventList.forEach(({ name, date, category, seats }) => {
      const card = document.createElement("div");
      card.style.border = "1px solid #ccc";
      card.style.margin = "10px";
      card.style.padding = "10px";
  
      card.innerHTML = `
        <h3>${name}</h3>
        <p>Date: ${date}</p>
        <p>Category: ${category}</p>
        <p>Seats: ${seats}</p>
      `;
  
      const regBtn = document.createElement("button");
      regBtn.textContent = "Register";
      regBtn.disabled = seats === 0;
      regBtn.onclick = () => {
        // Find event in original array and decrement seats if possible
        const eventIndex = events.findIndex(e => e.name === name);
        if (events[eventIndex].seats > 0) {
          events[eventIndex].seats--;
          renderEvents(); // re-render to update UI
        }
      };
  
      card.appendChild(regBtn);
      container.appendChild(card);
    });
  };
  
  // Filter events by category, cloning array before filtering
  const filterByCategory = (category = "all") => {
    let filtered = [...events];
    if (category !== "all") {
      filtered = filtered.filter(e => e.category === category);
    }
    renderEvents(filtered);
  };
  
  // Event listener for category filter dropdown
  document.querySelector("#category-filter").addEventListener("change", e => {
    filterByCategory(e.target.value);
  });
  
  // Initial render on page load
  window.onload = () => renderEvents();
  