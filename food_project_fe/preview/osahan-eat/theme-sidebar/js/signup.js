$(document).ready(function(){

    $("#btn-signup").click(function() {
        var data = {
            fullname: $("#fullname").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }
        //Call API raw json
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/auth/signup",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function( result ) {
            if (result.success) {
                localStorage.setItem("auth", result.data.token)
                console.log("sucess")
                window.location.href = "index.html"

            } else {
                console.log("fail")
            }
        });

    });
});