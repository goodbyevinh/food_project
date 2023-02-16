$(document).ready( function(){


    const handleAjax = () => {
        var cartData = []
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/order/get-cart",
            headers: {
                "Authorization":"Bearer " + localStorage.getItem("auth")
            },
            async: false,
            success: function( result,status, xhr ) {
                if (result.success) {
                    cartData = result.data

                } else {
                    console.log("fail")
                }
            },
            error : function(xhr) {
            }
        })
        handleCart(cartData)
    }
    handleAjax()

    
})

const handleCart = (cartData) => {
    console.log(cartData)

}