const md = window.markdownit({
    html: false,
    linkify: true,
    typographer: true,
    breaks: true
});

const contents = document.getElementsByClassName("content");
const csrf = document.getElementById("csrf");

[...contents].forEach(content => {
    content.innerHTML = md.render(content.innerHTML);
})

const codeblocks = document.querySelectorAll("pre > code");

codeblocks.forEach(element => {
    element.innerText = element.innerText
        .replaceAll('&amp;', '&')
        .replaceAll('&lt;', "<")
        .replaceAll('&gt;', '>')
        .replace(' ', '\u00a0s');
})

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

document.querySelectorAll("[id^='edit-']").forEach(button => {
    button.addEventListener("click", async (e) => {
        showEditBox(e.target , button.id.slice("edit-".length))
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

async function editPost(postId, content) {
    let request = new XMLHttpRequest();
    let payload = new FormData();
    payload.append(csrf.name, csrf.value);
    payload.append("content", content.trim());
    request.open("PUT", "/posts/" + postId, true);
    request.send(payload);
    request.onreadystatechange = () => {
        if (request.readyState === 4 && request.status === 200) {
            window.location.reload();
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
    text.className = "block resize-none w-full px-4 py-4 lg:py-2 border-2 rounded";

    let button = document.createElement("button");
    button.className = "w-full text-left mt-2 block text-4xl lg:text-base px-2 underline decoration-java-blue underline-offset-4";
    button.type = "submit";
    button.textContent = "Send"

    let input = document.createElement("input");

    input.type = "hidden";
    input.name = csrf.name;
    input.value = csrf.value;

    form.append(label, text, button, input);
    form.className = "ml-2 mb-2 lg:mb-4 px-4 border-l-2 border-java-pink/80";

    target.parentNode.parentNode.after(form);

    if (window.areaHelper) areaHelper(text);
}

function showEditBox(editButton, postId) {
    const formId = `edit-form-${postId}`;
    const editContentId = `edit-content-${postId}`;
    const cancelEditId = `cancel-edit-${postId}`;
    const submitEditId = `submit-edit-${postId}`;

    let formHTML = `
        <div id="${formId}">
            <textarea id="${editContentId}" class="block resize-none w-full px-4 py-4 lg:py-2 border-2 rounded"></textarea>
            <button class="text-left text-4xl lg:text-base ml-2 lg:ml-4 mt-2 pb-4 text-black/70 underline underline-offset-8 decoration-java-blue/80 hover:decoration-2" id="${submitEditId}">Save</button>
            <button class="text-left text-4xl lg:text-base ml-2 lg:ml-4 mt-2 pb-4 text-black/70 underline underline-offset-8 decoration-java-pink/80 hover:decoration-2" id="${cancelEditId}">Cancel</button>
        </div>
    `
    const content = document.getElementById(postId);
    const originalContent = document.getElementById(`source-${postId}`)
    const converted = (new DOMParser()).parseFromString(formHTML, "text/html").firstChild
    content.after(converted);
    const form = document.getElementById(formId);
    const area = document.getElementById(editContentId);

    content.classList.add("hidden");
    editButton.classList.add("hidden");
    area.value = originalContent.innerHTML.trim();

    if (window.areaHelper) window.areaHelper(area);

    document.getElementById(cancelEditId).addEventListener("click", () => {
        form.parentNode.removeChild(form);
        content.classList.remove("hidden");
        editButton.classList.remove("hidden");
    })

    document.getElementById(submitEditId).addEventListener("click", async () => {
        await editPost(postId, area.value)
    })
}