$(document).ready(function(){

    $("#btn-signin").click(function() {
        var data = {
            username: $("#email").val(),
            password: $("#password").val()
        }
        //Call API raw json
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/auth/signin",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function( result,status  ) {
                if (result.success) {
                    localStorage.setItem("auth", result.data.token)
                    window.location.href = "index.html"
                } else {
                    console.log("fail")
                }
            },
            error : function(xhr) {

            }
        })

    });
});