const profile_dropdown_button = document.getElementById("profile-quick-access");
const profile_dropdown_content = document.getElementById("profile-dropdown");
const navbar_toggle = document.getElementById("navbar-toggle");
const navbar_content = document.getElementById("navbar-content");

if (profile_dropdown_button) profile_dropdown_button.addEventListener("mouseup", function (e) {
    e.preventDefault();
    profile_dropdown_content.classList.toggle("lg:hidden");
});

if (navbar_toggle) navbar_toggle.addEventListener("mouseup", function (e) {
    e.preventDefault();
    navbar_content.classList.toggle("hidden");
    navbar_toggle.children[0].classList.toggle("invisible");
});