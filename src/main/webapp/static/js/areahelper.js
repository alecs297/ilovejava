// https://stackoverflow.com/a/25621277

const tx = document.getElementsByTagName("textarea");
for (let i = 0; i < tx.length; i++) {
    areaHelper(t[i]);
}

function OnInput() {
    this.style.height = 0;
    this.style.height = (this.scrollHeight) + "px";
}

function areaHelper(area) {
    area.setAttribute("style", "height:" + (area.scrollHeight) + "px;overflow-y:hidden;");
    area.addEventListener("input", OnInput, false);
}