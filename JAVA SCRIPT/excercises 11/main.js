const form = document.getElementById("registration-form");

form.addEventListener("submit", function (event) {
  event.preventDefault(); // Prevent page reload

  // Clear previous errors
  document.getElementById("error-name").textContent = "";
  document.getElementById("error-email").textContent = "";
  document.getElementById("error-event").textContent = "";

  // Access form elements
  const { name, email, event: selectedEvent } = form.elements;

  let isValid = true;

  // Name validation
  if (!name.value.trim()) {
    document.getElementById("error-name").textContent = "Name is required.";
    isValid = false;
  }

  // Email validation (simple regex)
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email.value.trim()) {
    document.getElementById("error-email").textContent = "Email is required.";
    isValid = false;
  } else if (!emailPattern.test(email.value.trim())) {
    document.getElementById("error-email").textContent = "Enter a valid email.";
    isValid = false;
  }

  // Event selection validation
  if (!selectedEvent.value) {
    document.getElementById("error-event").textContent = "Please select an event.";
    isValid = false;
  }

  if (isValid) {
    alert(`Thank you, ${name.value}! You are registered for ${selectedEvent.value}.`);
    form.reset();
  }
});
