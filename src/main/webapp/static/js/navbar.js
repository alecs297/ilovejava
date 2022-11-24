const profile_dropdown_button = document.getElementById("profile-quick-access");
const profile_dropdown_content = document.getElementById("profile-dropdown");
const navbar_toggle = document.getElementById("navbar-toggle");
const navbar_content = document.getElementById("navbar-content");

profile_dropdown_button.addEventListener("click", function () {
    profile_dropdown_content.classList.toggle("lg:hidden");
});

navbar_toggle.addEventListener("click", function () {
    navbar_content.classList.toggle("hidden");
    navbar_toggle.children[0].classList.toggle("invisible");
});