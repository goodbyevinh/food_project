$(document).ready(function(){
    const paramValue = window.location.search
    const urlParams = new URLSearchParams(paramValue)
    const restaurantId = urlParams.get("id")
    var restaurantDetailData = {}
    var categoryNameData = []
    var foodData = []
    var foodDetailData = {}


    var handleRestaurantDetail = () => {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/api/restaurant/${restaurantId}`,
            contentType: "application/json; charset=utf-8"
        }).done(function( result,status, xhr ) {
            if (result.success) {
                restaurantDetailData = result.data
            } else {
                console.log("fail handleRestaurantDetail")
            }
        });
    }
    var handleGetCategoryName = () => {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/api/category/${restaurantId}`,
            contentType: "application/json; charset=utf-8"
        }).done(function( result,status, xhr ) {
            if (result.success) {
                categoryNameData = result.data
            } else {
                console.log("fail handleGetCategoryName")
            }
        });
    }

    var handleGetFoodsByCategoryName = async() => {
        await $.ajax({
            method: "GET",
            url: `http://localhost:8080/api/food/${restaurantId}`,
            contentType: "application/json; charset=utf-8"
        }).done(function( result,status, xhr ) {
            if (result.success) {
                foodData = result.data
            } else {
                console.log("fail handleGetFoodsByCategoryName")
            }
        });
    }


    var handleData = () => {
        var htmlDetail = `
                        <img src="${restaurantDetailData.image}" class="img-fluid border p-2 mb-auto rounded brand-logo shadow-sm">
                        <div class="ml-3">
                            <h3 class="mb-0 font-weight-bold">${restaurantDetailData.title} <small><i
                                    class="mdi mdi-silverware-fork-knife ml-2 mr-1"></i> ${restaurantDetailData.category}</small>
                            </h3>

                            <p class="text-muted p-0 mt-2 mb-2">${restaurantDetailData.desc}</p>
                            <p class="mb-0 small">
                                <i class="mdi mdi-star text-warning"></i> <span
                                    class="font-weight-bold text-dark">${restaurantDetailData.avgRate}</span> - ${restaurantDetailData.review} Ratings
                            </p>
                        </div>
                        `
        $("#restaurant-detail").append(htmlDetail)
        categoryNameData.map((category, index) => {
            var htmltab = ""
            var htmlpanel = ""
            if (index == 0) {
                htmltab = `
                            <li class="nav-item mr-2" role="presentation">
                                <a class="nav-link active border-0 btn btn-light" id="${category}-tab" data-toggle="tab"
                                href="#${category}" role="tab" aria-controls="${category}" aria-selected="true">${category}</a>
                            </li>
                            `
                htmlpanel = `
                            <div class="tab-pane fade show active" id="${category}" role="tabpanel" aria-labelledby="${category}-tab">
                                <div class="row">
                                </div>
                            </div>
                            `
            } else {
                htmltab = `
                            <li class="nav-item mx-2" role="presentation">
                                <a class="nav-link border-0 btn btn-light" id="${category}-tab" data-toggle="tab" href="#${category}"
                                role="tab" aria-controls="${category}" aria-selected="false">${category}</a>
                            </li>
                        `
                htmlpanel = `
                                <div class="tab-pane fade" id="${category}" role="tabpanel" aria-labelledby="${category}-tab">
                                    <div class="row">
                                    </div>
                                </div>
                            `
            }
            
            $("#myTab").append(htmltab)
            $("#myTabContent").append(htmlpanel)
        })
        foodData.map((food, index) => {
            var html = ""
            html = `
                        <a foodId="${food.id}" href="#"  class="text-decoration-none text-dark col-xl-4 col-md-12 mb-4 btn-food-detail"
                            data-toggle="modal" data-target="#myitemsModal">
                            <img src="${food.image}" class="img-fluid rounded">
                            <div class="d-flex align-items-center mt-3 mb-2">
                                <p class="text-black h6 m-0">${food.name}</p>
                                
                            </div>
                            <p class="small mb-2"><i class="mdi mdi-star text-warning"></i> <span
                                    class="font-weight-bold text-dark ml-1">${food.avgRate}</span>(${food.review}) <i
                                    class="mdi mdi-silverware-fork-knife ml-2 mr-1"></i> ${food.category} 
                            </p>
                        </a>    
                       
                    `
                $(`#${food.category} .row`).append(html)
        })
    }
    var handleModal = async(id) => {
        await $.ajax({
            method: "GET",
            url: `http://localhost:8080/api/food/detail/${id}`,
            contentType: "application/json; charset=utf-8"
        }).done(function( result,status, xhr ) {
            if (result.success) {
                foodDetailData = result.data
            } else {
                console.log("fail handleModal")
            }
        });
    }
    var handleDetailFood = () => {
        $("#img-detail").attr("src", foodDetailData.image)
        $("#title-detail").text(foodDetailData.title)
        $("#desc-detail").text(foodDetailData.description)
        $("#price-detail").text(`Add ${foodDetailData.price} $`)
        foodDetailData.foodAddons.map(foodon => {
            var html = ` 
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" name="foodon" value="${foodon.id}" id=${foodon.id}>
                                <label class="custom-control-label font-weight-bold text-dark" for="${foodon.id}">${foodon.name}</label>
                            </div>
                        `
            $("#foodon-detail").append(html)
        })
    }
    var handleFavRestaurant = () => {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/api/user/favourite/restaurant/${restaurantId}`,
            headers: {
                "Authorization":"Bearer " + localStorage.getItem("auth")
            }
    
        }).done(function( result,status, xhr ) {
            var html = ""
            if (result.success) {
                html =  `
                            <a href="#"><i class="mdi mdi-heart"></i></a>
                        `
            } else {
                html = `
                            <a href="#"><i class="far fa-heart"></i></a>
                        `
            }
            $("#fav-restaurant").empty().append(html)
        });
    }
    var handleFavFood = (foodId) => {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/api//user/favourite/food/${foodId}`,
            headers: {
                "Authorization":"Bearer " + localStorage.getItem("auth")
            }
    
        }).done(function( result,status, xhr ) {
            if (result.success) {

            } else {
                console.log("fail")
            }
        });
    }
    var handleAjax = async() => {
        handleRestaurantDetail()
        handleGetCategoryName()
        await handleGetFoodsByCategoryName();
    }

    var handleExecute = async() => {
        await handleAjax()
        await handleFavRestaurant()
        handleData()
        $(".btn-food-detail").click(async function() {
            var id = $(this).attr("foodId")
            await handleModal(id)
            handleDetailFood()
        })
        $('#myitemsModal').on('hide.bs.modal', function (e) {
            console.log("hide")
            $("#foodon-detail").empty()
        })
    }
    
    handleExecute();


});