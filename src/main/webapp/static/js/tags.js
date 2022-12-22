const csrf = document.getElementById("csrf");

function buildData(parent) {
    let data = new FormData();
    [...parent.querySelectorAll(":scope > td > input")]
        .forEach(el => data.append(el.name, el.value))
    data.append(csrf.name, csrf.value);
    return data;
}

function getId(current) {
    return current.parentNode.parentNode.id.slice("edit-tag-".length);
}

document.querySelectorAll("[id^='save-tag-']").forEach(button => {
    button.addEventListener("click", async (e) => {
        await updateTag(getId(e.target), buildData(e.target.parentNode.parentNode))
    })
})

document.querySelectorAll("[id^='delete-tag-']").forEach(button => {
    button.addEventListener("click", async (e) => {
        await deleteTag(getId(e.target), buildData(e.target.parentNode.parentNode))
    })
})

document.getElementById("create-tag").addEventListener("click", async (e) => {
    await createTag(buildData(e.target.parentNode.parentNode));
})

async function updateTag(tagId, data) {
    let request = new XMLHttpRequest();
    request.open("PUT", "/tags/" + tagId, true);
    request.onreadystatechange = () => {
        if (request.readyState === 4) {
            if (request.status === 200) window.location.reload();
            else alert("An error occurred.")
        }
    }
    request.send(data);

}

async function deleteTag(tagId, data) {
    if (!confirm("Are you sure you want to delete '" + tagId + "' ?")) return
    let request = new XMLHttpRequest();
    request.open("DELETE", "/tags/" + tagId, true);
    request.send(data);
    request.onreadystatechange = () => {
        if (request.readyState === 4) {
            if (request.status === 200) window.location.reload();
            else alert("An error occurred.")
        }
    }
}

async function createTag(data) {
    let request = new XMLHttpRequest();
    request.open("POST", "/tags");
    request.onreadystatechange = () => {
        if (request.readyState === 4) {
            if (request.status === 200) window.location.reload();
            else alert(request.responseText)
        }
    }
    request.send(data);

}

