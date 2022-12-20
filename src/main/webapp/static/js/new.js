const preview_btn = document.getElementById("preview-btn");
const preview_div = document.getElementById("preview-content");
const content_div = document.getElementById("content");
const md = window.markdownit({
    html: true,
    linkify: true,
    typographer: true,
    breaks: true
});
preview_btn.addEventListener("click", () => {
    preview_div.classList.toggle("hidden");
    content_div.classList.toggle("hidden");
    if (preview_btn.checked) {
        preview_div.innerHTML = md.render(DOMPurify.sanitize(content_div.value));
    }
})