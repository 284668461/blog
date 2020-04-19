$(()=>{

    //初始化 下拉
    $(".ui.dropdown").dropdown();



    //初始化tab
    $('.menu .item')
        .tab()
    ;



//     var testEditor;
//     testEditor = editormd("test-editormd", {
//         placeholder:'本编辑器支持Markdown编辑，左边编写，右边预览',  //默认显示的文字，这里就不解释了
//         width: "90%",
//         height: 640,
//         syncScrolling: "single",
//         path: "lib/editor/lib/",   //你的path路径（原资源文件中lib包在我们项目中所放的位置）
//         // theme: "dark",//工具栏主题
//         // previewTheme: "dark",//预览主题
//         // editorTheme: "pastel-on-dark",//编辑主题
//         // saveHTMLToTextarea: true,
//         // emoji: false,
//         // taskList: true,
//         // tocm: true,         // Using [TOCM]
//         // tex: true,                   // 开启科学公式TeX语言支持，默认关闭
//         // flowChart: true,             // 开启流程图支持，默认关闭
//         // sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
//         // toolbarIcons : function() {  //自定义工具栏，后面有详细介绍
//         //     return editormd.toolbarModes['simple']; // full, simple, mini
//         // },
//     });
// //上面的挑有用的写上去就行





    let  testEditor = editormd("iEditormd",{

        width:"100%",
        height:500,
        syncScrolling: "single",
        path:"lib/editor/lib/",


        saveHTMLToTextarea : true,//注意3：这个配置，方便post提交表单

        /**上传图片相关配置如下*/
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/smart-api/upload/editormdPic/",//注意你后端的上传图片服务地址

    });


//    封面上传预览start


    $("#upImg").on('click', ()=>{
        $("#fileUpload").click();

    });


    $("#fileUpload").on('change', function () {

        var image_holder = $("#image-holder");
        image_holder.empty();

        var reader = new FileReader();
        reader.onload = function (e) {
            $("<img />", {
                "src": e.target.result,
                "class": "thumb-image  ui rounded image center aligned"
            }).appendTo(image_holder);

        };
        image_holder.show();
        reader.readAsDataURL($(this)[0].files[0]);

    });
//    封面上传预览end



});
