function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

function changeTheme() {
    let current = getTheme();
    document.querySelector('html').classList.add(current);
    let changeButtons = document.querySelectorAll('#theme_change, #theme_change1');

    changeButtons.forEach(button => {
        button.addEventListener("click", () => {
            console.log("change");
            let old = current;
            current = (current === 'dark') ? 'light' : 'dark';
            setTheme(current);
            document.querySelector('html').classList.remove(old);
            document.querySelector('html').classList.add(current);
            let text = (current === 'dark') ? "Light" : "Dark";
            button.querySelector('span').textContent = text;
        });
    });
}

document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
});
