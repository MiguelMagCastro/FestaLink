
document.addEventListener('DOMContentLoaded', function () {
    const toggleSwitch = document.getElementById('proprietario');

    toggleSwitch.addEventListener('change', function () {
        if (toggleSwitch.checked) {
            console.log("Usuário é proprietário");
        } else {
            console.log("Usuário não é proprietário");
        }
    });
});
