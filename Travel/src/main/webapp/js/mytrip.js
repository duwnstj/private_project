document.addEventListener('DOMContentLoaded', function() {

var btn1 = document.getElementById("btn_1");
var btn2 = document.getElementById("btn_2");
var btn3 = document.getElementById("btn_3");
var btn4 = document.getElementById("btn_4");

    btn1.addEventListener("click", function() {
        window.location.assign("addschedule");
    });

    btn2.addEventListener("click", function() {
        window.location.assign("mytrip");
    });

    btn3.addEventListener("click", function() {
        window.location.assign("");
    });

    btn4.addEventListener("click", function() {
        window.location.assign("");
    });
        var posts = document.getElementById('postsContainer');
        
        posts.addEventListener('click', function(event) {
            // Check if the click target has the class 'viewDetailsBtn'
            if (event.target.classList.contains('viewDetailsBtn')) {
                var postId = event.target.closest('.post').getAttribute('data-plan-no');
                var form = document.getElementById('form_' + postId);
                form.submit(); // Submit the form
            }
        });
});