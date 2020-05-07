$(()=>{

    $("#sidebar").on("click",()=>{

        $('.m-item').toggleClass("m-mobile-hide");
    });




    $('button.PlayTourBtn')
        .popup({
            popup : $('div.payShow'),
            on    : 'click',
            position:"top center"
        });





    let blog = new Vue({
        el:"#center"
    });

});
