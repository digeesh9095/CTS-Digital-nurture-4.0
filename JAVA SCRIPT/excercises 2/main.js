// Event details
const eventName = "Local Farmers Market";
const eventDate = "2025-06-10";
let availableSeats = 50;

// Display event info on page load
window.onload = function() {
    alert("Page is fully loaded!");
    displayEventDetails();
};

// Function to show event info
function displayEventDetails() {
    const eventDetails = `
        <p><strong>Event:</strong> ${eventName}</p>
        <p><strong>Date:</strong> ${eventDate}</p>
        <p><strong>Available Seats:</strong> ${availableSeats}</p>
    `;
    document.getElementById("event-details").innerHTML = eventDetails;
    console.log(`Event: ${eventName}, Date: ${eventDate}, Seats: ${availableSeats}`);
}

// Simulate registration
function register() {
    if (availableSeats > 0) {
        availableSeats--;
        alert("Registered successfully!");
    } else {
        alert("No seats available!");
    }
    displayEventDetails();
}

// Simulate cancellation
function cancel() {
    availableSeats++;
    alert("Registration cancelled.");
    displayEventDetails();
}
