$(()=>{

    //导航条 start---------------------------
    $("#sidebar").on("click",()=>{

        $('.m-item').toggleClass("m-mobile-hide");
    });



    //切换 section
    $("#nav a.item").click(function(){


        let thisLabel = $(this).attr("data-section");

        $(this).addClass("active").siblings().removeClass("active");

        $(`section.${thisLabel}`).show(300).siblings().hide(300);




    });

    //导航条 end---------------------------


//登录 start ---------------------------

    $("#loginBtn").on("click",()=>{

        $('#login')
            .modal('show')
        ;


    });

//登录 end   ---------------------------






});
