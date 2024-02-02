/*window.addEventListener('DOMContentLoaded', function() {
setTimeout(function() {
    document.querySelector("#preloader").style.display = "none";
}, 1400);
})*/

document.querySelector('.confirm_button').addEventListener('click', function() {
    setTimeout(function() {
        document.querySelector("#preloader").style.display = "block";
    }, 1000);
});

