$(document).ready(function (){

        // // Thêm html vào selector chỉ định
    // $(".btn-update").click(function (){
    //     alert("okeeee")
    //     var html = `
    //                 <tr>
    //                     <td id="role_id" >3333</td>
    //                     <td>3333</td>
    //                     <td>3333</td>
    //                     <td>
    //                         <a href="#" class="btn btn-sm btn-primary btn-update">Sửa</a>
    //                         <a href="#" roleId="99" class="btn btn-sm btn-danger btn-delete">Xóa</a>
    //                     </td>
    //                 </tr>
    //     `
    //     $("#role-body").append(html)
    // }) 

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/category/explorelimit",
        contentType: "application/json; charset=utf-8"
    }).done(function( result,status, xhr ) {
        if (result.success) {
            var exploreData = result.data
            exploreData.map(data => {
                var html = `
                            <a href="listing.html" class="text-decoration-none col-xl-2 col-md-4 mb-4">
                                <div class="rounded py-4 bg-white shadow-sm text-center">
                                    <img src="${data.image}" class="mdi text-white osahan-icon mx-auto rounded-pill" alt="">
                                    <h6 class="mb-1 mt-3">${data.title}</h6>
                                    <p class="mb-0 small">${data.option}</p>
                                </div>
                            </a>
                `
                $("#explore").append(html)
            })
        
        } else {
            console.log("fail")
        }
    });


    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/restaurant/limit",
        contentType: "application/json; charset=utf-8"
    }).done(function( result,status, xhr ) {
        if (result.success) {
            var restaurantData = result.data
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
                $("#restaurant").append(html)
            })
        
        } else {
            console.log("fail")
        }
    });

    $.ajax({
        method: "POST",
        url: "http://localhost:8080/api/food/asianfoodlimit",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: jQuery.param({ id: 7})

    }).done(function( result,status, xhr ) {
        if (result.success) {
            var foodData = result.data
            foodData.map(data => {
                var html = `
                            <a href="#" class="text-decoration-none col-xl-4 col-md-4 mb-4" data-toggle="modal"
                            data-target="#myitemsModal">
                                <img src="${data.image}" class="img-fluid rounded">
                                <div class="d-flex align-items-center mt-3">
                                    <p class="text-black h6 m-0">${data.name}</p>
                                    <span class="badge badge-light ml-auto"></span>
                                </div>
                            </a>
                `
                $("#food").append(html)
            })
        
        } else {
            console.log("fail")
        }
    });

    $("#all-category").click( function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/category/explore",
            contentType: "application/json; charset=utf-8"
        }).done(function( result,status, xhr ) {
            if (result.success) {
                $("#explore").empty()
                var exploreData = result.data
                exploreData.map(data => {
                    var html = `
                                <a href="listing.html" class="text-decoration-none col-xl-2 col-md-4 mb-4">
                                    <div class="rounded py-4 bg-white shadow-sm text-center">
                                        <img src="${data.image}" class="mdi text-white osahan-icon mx-auto rounded-pill" alt="">
                                        <h6 class="mb-1 mt-3">${data.title}</h6>
                                        <p class="mb-0 small">${data.option}</p>
                                    </div>
                                </a>
                    `
                    $("#explore").append(html)
                })
            
            } else {
                console.log("fail")
            }
        });
    })

    $("#all-restaurant").click( function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/restaurant",
            contentType: "application/json; charset=utf-8"
        }).done(function( result,status, xhr ) {
            if (result.success) {
                $("#restaurant").empty()
                var restaurantData = result.data
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
                    $("#restaurant").append(html)
                })
            
            } else {
                console.log("fail")
            }
        });
    })
    $("#all-food").click( function() {
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/food/asianfood",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data: jQuery.param({ id: 7})
        }).done(function( result,status, xhr ) {
            if (result.success) {
                $("#food").empty()
                var foodData = result.data
                foodData.map(data => {
                    var html = `
                                <a href="#" class="text-decoration-none col-xl-4 col-md-4 mb-4" data-toggle="modal"
                                data-target="#myitemsModal">
                                    <img src="${data.image}" class="img-fluid rounded">
                                    <div class="d-flex align-items-center mt-3">
                                        <p class="text-black h6 m-0">${data.name}</p>
                                        <span class="badge badge-light ml-auto"></span>
                                    </div>
                                </a>
                    `
                    $("#food").append(html)
                })
            
            } else {
                console.log("fail")
            }
        });
    })
})