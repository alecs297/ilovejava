const md = window.markdownit({
    html: true,
    linkify: true,
    typographer: true,
    breaks: true
});

const contents = document.getElementsByClassName("content");
[...contents].forEach(content => {
    content.innerHTML = md.render(content.innerHTML);
})
const csrf = document.getElementById("csrf");

document.querySelectorAll("[id^='reply-']").forEach(button => {
    button.addEventListener("click", (e) => {
        showReplyBox(e.target, button.id.slice("reply-".length))
    })
})

document.querySelectorAll("[id^='remove-']").forEach(button => {
    button.addEventListener("click", async () => {
        await deletePost(button.id.slice("remove-".length))
    })
})

document.querySelectorAll("[id^='vote-']").forEach(button => {
    button.addEventListener("click", async (e) => {
        await vote(e.target, button.id.slice("vote-".length))
    })
})

async function deletePost(postId) {
    if (!confirm("Are you sure you want to delete this post?")) return
    let request = new XMLHttpRequest();
    let payload = new FormData();
    payload.append(csrf.name, csrf.value);
    request.open("DELETE", "/posts/" + postId, true);
    request.send(payload);
    request.onreadystatechange = () => {
        if (request.readyState === 4 && request.status === 200) {
            window.location.reload();
        }
    }
}

async function vote(target, postId) {
    let request = new XMLHttpRequest();
    let payload = new FormData();
    payload.append(csrf.name, csrf.value);
    request.open("POST", "/posts/" + postId + "/vote", true);
    request.send(payload);
    request.onreadystatechange = () => {
        if (request.readyState === 4 && request.status === 200) {
            target.classList.toggle("text-java-pink");
            target.innerHTML = request.responseText + " - Vote";
        }

    }
}

function showReplyBox(target, postId) {

    document.querySelectorAll("[id^='replybox-']")
        .forEach(box => box.parentNode.removeChild(box));

    let form = document.createElement("form");
    form.action = '/posts/' + postId;
    form.method = "post";
    form.id = "replybox-" + postId;

    let label = document.createElement("label");
    label.setAttribute("for", "content" + postId);

    let text = document.createElement("textarea");
    text.name = "content";
    text.id = "text-" + postId;
    text.className = "block resize-none w-full px-4 py-4 lg:py-2 border-2 rounded content";

    let button = document.createElement("button");
    button.className = "w-full mt-2 block text-4xl lg:text-base px-8 underline decoration-wavy decoration-java-blue underline-offset-4";
    button.type = "submit";
    button.textContent = "Send"

    let input = document.createElement("input");

    input.type = "hidden";
    input.name = csrf.name;
    input.value = csrf.value;

    form.append(label, text, button, input);
    form.className = "ml-2 mb-2 lg:mb-4 px-4 border-l-2 border-java-pink/80";

    target.parentNode.parentNode.after(form);
}