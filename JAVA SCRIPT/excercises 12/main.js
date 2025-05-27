const form = document.getElementById("registration-form");
const messageDiv = document.getElementById("message");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  // Clear messages
  messageDiv.textContent = "";
  document.getElementById("error-name").textContent = "";
  document.getElementById("error-email").textContent = "";
  document.getElementById("error-event").textContent = "";

  const { name, email, event: selectedEvent } = form.elements;

  let isValid = true;

  if (!name.value.trim()) {
    document.getElementById("error-name").textContent = "Name is required.";
    isValid = false;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email.value.trim()) {
    document.getElementById("error-email").textContent = "Email is required.";
    isValid = false;
  } else if (!emailPattern.test(email.value.trim())) {
    document.getElementById("error-email").textContent = "Enter a valid email.";
    isValid = false;
  }

  if (!selectedEvent.value) {
    document.getElementById("error-event").textContent = "Please select an event.";
    isValid = false;
  }

  if (!isValid) return;

  // Prepare data
  const userData = {
    name: name.value.trim(),
    email: email.value.trim(),
    event: selectedEvent.value
  };

  // Show loading message
  messageDiv.textContent = "Submitting your registration...";

  try {
    // Simulate network delay using setTimeout wrapped in a Promise
    await new Promise(resolve => setTimeout(resolve, 1500));

    // Simulate POST fetch call to mock API (replace URL with your API)
    const response = await fetch("https://jsonplaceholder.typicode.com/posts", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(userData)
    });

    if (!response.ok) throw new Error("Network response was not ok");

    const data = await response.json();

    messageDiv.style.color = "green";
    messageDiv.textContent = `Thank you, ${data.name}! Your registration for "${data.event}" was successful.`;
    form.reset();
  } catch (error) {
    messageDiv.style.color = "red";
    messageDiv.textContent = `Registration failed: ${error.message}`;
  }
});
