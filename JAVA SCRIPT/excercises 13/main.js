const form = document.getElementById("registration-form");
const messageDiv = document.getElementById("message");

form.addEventListener("submit", async (event) => {
  event.preventDefault();
  console.log("Form submission started");

  // Clear messages and errors
  messageDiv.textContent = "";
  messageDiv.style.color = "black";
  document.getElementById("error-name").textContent = "";
  document.getElementById("error-email").textContent = "";
  document.getElementById("error-event").textContent = "";

  const { name, email, event: selectedEvent } = form.elements;

  let isValid = true;

  console.log("Validating inputs...");

  // Name validation
  if (!name.value.trim()) {
    document.getElementById("error-name").textContent = "Name is required.";
    console.log("Validation error: Name is empty");
    isValid = false;
  }

  // Email validation using regex
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email.value.trim()) {
    document.getElementById("error-email").textContent = "Email is required.";
    console.log("Validation error: Email is empty");
    isValid = false;
  } else if (!emailPattern.test(email.value.trim())) {
    document.getElementById("error-email").textContent = "Enter a valid email.";
    console.log("Validation error: Email format invalid");
    isValid = false;
  }

  // Event selection validation
  if (!selectedEvent.value) {
    document.getElementById("error-event").textContent = "Please select an event.";
    console.log("Validation error: No event selected");
    isValid = false;
  }

  if (!isValid) {
    console.log("Form validation failed; submission aborted.");
    return;
  }
  console.log("Validation passed");

  const userData = {
    name: name.value.trim(),
    email: email.value.trim(),
    event: selectedEvent.value,
  };
  console.log("User data to submit:", userData);

  // Show loading message
  messageDiv.style.color = "black";
  messageDiv.textContent = "Submitting your registration...";

  try {
    console.log("Simulating network delay...");
    await new Promise((resolve) => setTimeout(resolve, 1500));

    console.log("Sending fetch POST request...");
    const response = await fetch("https://jsonplaceholder.typicode.com/posts", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userData),
    });

    console.log("Fetch response received:", response);

    if (!response.ok) {
      throw new Error(`Network response was not ok (status ${response.status})`);
    }

    const data = await response.json();
    console.log("Response JSON parsed:", data);

    messageDiv.style.color = "green";
    messageDiv.textContent = `Thank you, ${data.name}! Your registration for "${data.event}" was successful.`;
    form.reset();
  } catch (error) {
    console.error("Fetch error:", error);
    messageDiv.style.color = "red";
    messageDiv.textContent = `Registration failed: ${error.message}`;
  }
});
