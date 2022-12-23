const button = document.getElementById("check-button");
const username = document.getElementById("username");
const previous = {};

let checking = false;

async function checkUsername(usernameValue) {
    if (!/^[a-zA-Z0-9_]{4,16}$/.test(usernameValue)) {
        button.innerText = "Invalid";
    } else if (!checking) {
        if (!previous[usernameValue]) {
            checking = true;
            button.disabled = true;
            button.innerText = "Checking...";

            const response = await fetch("/check-username-available/" + usernameValue);
            previous[usernameValue] = await response.json();

            button.disabled = false;
            setTimeout(() => {
                checking = false;
            }, 100);
        }
        button.innerText = previous[usernameValue] ? "Available" : "Taken";
    }
}

username.addEventListener("keyup", async () => {
    try {
        await checkUsername(username.value);
    } catch (e) {
        button.innerText = "Check";
    }
});
button.addEventListener("click", async (e) => {
    e.preventDefault();
    await checkUsername(username.value);
});