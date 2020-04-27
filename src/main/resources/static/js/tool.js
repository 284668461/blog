

/*
 * @Description 仿android toast 提示
 * @Author 284668461@qq.com
 * @Date 16:14 2020/4/21
 * @Param
 * @return
 **/
function Toast(msg,time){

    time = time===null? 2000:isNaN(time)?2000:time;

    var m = document.createElement('div');
    m.innerHTML = msg;
    m.style.cssText="width: 60%;" +
        "min-width: 150px;" +
        "opacity: 0.8;" +
        "height: 40px;" +
        "color: rgb(255, 255, 255);" +
        "line-height: 40px;" +
        "text-align: center;" +
        "border-radius: 15px;" +
        "position: fixed;" +
        "top: 40%;" +
        "left: 20%;" +
        "z-index: 999999;" +
        "background: rgb(0, 0, 0);" +
        "pointer-events: none;" +
        "font-size: 2vm;";
    document.body.appendChild(m);
    setTimeout(function() {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, time);
}
