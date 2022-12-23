const help_button = document.getElementById("help");
help_button.addEventListener("click", function(e) {
    e.preventDefault();
    help_button.innerText = "Too bad, we don't have any SMTP server to help you with.";
});