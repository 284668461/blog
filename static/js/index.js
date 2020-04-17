$(()=>{


    $("#sidebar").on("click",()=>{

        $('.m-item').toggleClass("m-mobile-hide");
    });



//    登录事件

    $("#loginBtn").on("click",()=>{

        $('#login')
            .modal('show')
        ;


    });




});
