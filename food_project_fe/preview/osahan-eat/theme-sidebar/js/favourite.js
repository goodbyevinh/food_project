$(document).ready(function (){


    const handleRestaurant = (restaurantData) => {
        $("#home-tab").attr("class","nav-link active border-0 bg-primary text-white rounded")
        $("#profile-tab").attr("class","nav-link border-0 bg-light text-dark rounded ml-3")
        $("#home").attr("class","tab-pane fade show active")
        $("#profile").attr("class","tab-pane fade")
        $("#list-restaurant").empty()
        restaurantData.map(data => {
            var html = `
                        <a href="detail.html" class="text-dark text-decoration-none col-xl-4 col-lg-12 col-md-12">
                            <div class="bg-white shadow-sm rounded d-flex align-items-center p-1 mb-4 osahan-list">
                                <div class="bg-light p-3 rounded">
                                    <img src="${data.image}" class="img-fluid">
                                </div>
                                <div class="mx-3 py-2 w-100">
                                    <p class="mb-2 text-black">${data.title}</p>
                                    <p class="small mb-2">
                                        <i class="mdi mdi-star text-warning mr-1"></i> <span
                                            class="font-weight-bold text-dark">${data.avgRate}</span> (${data.review})
                                    </p>
                                </div>
                            </div>
                        </a>
            `
            $("#list-restaurant").append(html)

            
        })
    }
    const handleFood = (foodData) => {

        $("#profile-tab").attr("class","nav-link active border-0 bg-primary text-white rounded")
        $("#home-tab").attr("class","nav-link border-0 bg-light text-dark rounded ml-3")
        $("#profile").attr("class","tab-pane fade show active")
        $("#home").attr("class","tab-pane fade")

        $("#list-food").empty()

        foodData.map(data => {
            var html =  `
                            <a href="#" class="text-decoration-none text-dark col-xl-4 col-md-12 mb-4"
                               data-toggle="modal" data-target="#myitemsModal">
                                <img src="${data.image}" class="img-fluid rounded">
                                <div class="d-flex align-items-center mt-3 mb-2">
                                    <p class="text-black h6 m-0">${data.title}</p>
                                    <span class="badge badge-light ml-auto"><i class="mdi mdi-truck-fast-outline"></i> Free delivery</span>
                                </div>
                                <p class="small mb-2"><i class="mdi mdi-star text-warning"></i> <span
                                        class="font-weight-bold text-dark ml-1">${data.avgRate}</span>(${data.review}) <i
                                        class="mdi mdi-silverware-fork-knife ml-2 mr-1"></i> ${data.category} <i
                                        class="mdi mdi-motorbike ml-2 mr-2"></i>45
                                    - 55 min
                                </p>
                            </a>
                        `
            $("#list-food").append(html)
        })
    }

    var handleAjax =  () => {
        var restaurantData = []
        var foodData = []

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/restaurant/favourite",
            headers: {
                "Authorization":"Bearer " + localStorage.getItem("auth")
            },
            async: false,
            success: function( result,status, xhr ) {
                if (result.success) {
          
                    restaurantData = result.data
                    $("#home-tab").text(`Restaurants (${restaurantData.length})` ) 
                } else {
                    console.log("fail")
                }
            },
            error : function(xhr) {
            }
        })

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/food/favourite",
            headers: {
                "Authorization":"Bearer " + localStorage.getItem("auth")
            },
            async: false,
            success: function( result,status, xhr ) {
                if (result.success) {
           
                    foodData = result.data
                    $("#profile-tab").text(`Dishes (${foodData.length})` )
                
                } else {
                    console.log("fail")
                }
            },
            error : function(xhr) {
            }
        })

        handleRestaurant(restaurantData)
        $("#home-tab").click(() => handleRestaurant(restaurantData))
        $("#profile-tab").click(() => handleFood(foodData))
    }
    if (localStorage.getItem("auth")) {
        handleAjax()
    } else {
        window.location.href = "404.html"
    }





})