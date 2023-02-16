$(document).ready(function(){


    const handleUpcoming = (upcomingData) => {
        console.log(upcomingData)
        upcomingData.map(data => {

            var html  = `
                    <div class="col-lg-4 col-md-6 1">
                        <div class="bg-white shadow-sm rounded p-3 mb-4">
                            <div class="d-flex align-items-center mb-1">
                                <h6 class="mb-0">${data.id}</h6>
                                <p class="badge ${data.status === 'Order Preparing' ? 'badge-light' : 'badge-info'} mb-0 ml-auto"><i class="mdi mdi-check-circle"></i>
                                    ${data.status}</p>
                            </div>
                            <div class="d-flex align-items-center">
                                <p class="small">
                                <i class="mdi mdi-calendar mr-1"></i> ${data.time} </p>
                            </div>
                            ${
                                data.foods.map(food =>{
                                    return `<p class="text-dark mb-2"><span class="mr-2 text-black">${food.quantity}</span>${food.name}</p>`
                                })
                            }
  
                            <div class="d-flex align-items-center row pt-2 mt-3">
                                <div class="col-6 pr-2">
                                    <a href="#" class="btn btn-primary btn-block" data-toggle="modal"
                                    data-target="#detailsModal">Detials</a>
                                </div>
                                <div class="col-6 pl-2">
                                    <a href="settings.html" class="btn btn-outline-primary btn-block">Get help</a>
                                </div>
                            </div>
                        </div>
                    </div>
            `
            $("#list-upcoming").append(html)
        })
    }
    const handlePrevious = (previousData) => {
        //console.log(previousData)
        previousData.map(data => {
            var html  = `
                    <div class="col-lg-4 col-md-6 1">
                        <div class="bg-white shadow-sm rounded p-3 mb-4">
                            <div class="d-flex align-items-center mb-1">
                                <h6 class="mb-0">${data.id}</h6>
                                <p class="badge ${data.status === 'Completed' ? 'badge-success' : 'badge-danger'} mb-0 ml-auto"><i class="mdi mdi-check-circle"></i>
                                    ${data.status }</p>
                            </div>
                            <div class="d-flex align-items-center">
                                <p class="small">
                                    <i class="mdi mdi-calendar mr-1"></i> ${data.time} </p>
                            </div>
                            ${
                                data.foods.map(food =>{
                                    return `<p class="text-dark mb-2"><span class="mr-2 text-black">${food.quantity}</span>${food.name}</p>`
                                })
                            }
                            <div class="d-flex align-items-center row pt-2 mt-3">
                                <div class="col-6 pr-2">
                                    <a href="#" class="btn btn-primary btn-block" data-toggle="modal"
                                    data-target="#detailsModal">Detials</a>
                                </div>
                                <div class="col-6 pl-2">
                                    <a href="settings.html" class="btn btn-outline-primary btn-block">Get help</a>
                                </div>
                            </div>
                        </div>
                    </div>
            `
            $("#list-previous").append(html)
        })
    }
    const handleAjax = () => {
        var upcomingData = []
        var previousData = []
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/order/upcoming",
            headers: {
                "Authorization":"Bearer " + localStorage.getItem("auth")
            },
            async: false,
            success: function( result,status, xhr ) {
                if (result.success) {
                    upcomingData = result.data
        

                } else {
                    console.log("fail")
                }
            },
            error : function(xhr) {
            }
        })
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/order/previous",
            headers: {
                "Authorization":"Bearer " + localStorage.getItem("auth")
            },
            async: false,
            success: function( result,status, xhr ) {
                if (result.success) {
                    previousData = result.data
                    
                } else {
                    console.log("fail")
                }
            },
            error : function(xhr) {
            }
        })
        handlePrevious(previousData)
        handleUpcoming(upcomingData)
    }
    if (localStorage.getItem("auth")) {
        handleAjax()

    } else {
        window.location.href = "404.html"
    }
    
})