$(()=>{

    //初始化 下拉
    $(".ui.dropdown").dropdown();



    //初始化tab
    $('#tab .item').tab();


    let  testEditor = editormd("iEditormd",{

        width:"100%",
        height:500,
        syncScrolling: "single",
        path:"lib/editor/lib/",
        saveHTMLToTextarea : true,//注意3：这个配置，方便post提交表单

        /**上传图片相关配置如下*/
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/admin/uploadImg",//注意你后端的上传图片服务地址

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


    //初始化
    toLoadClassify();
    toLoadLabel();


// 发布 start  -------------------------

    /*
     * @Description 加载标签
     * @Author 284668461@qq.com
     * @Date 15:55 2020/4/26
     * @Param
     * @return
     **/
    function toLoadClassify(){

        $.post({
            url:"/blog/getClassify",
            success:(data)=>{

                loadClassify(JSON.parse(data));
            }
        });
    }


    /*
     * @Description 加载标签到界面
     * @Author 284668461@qq.com
     * @Date 15:55 2020/4/26
     * @Param
     * @return
     **/
    function loadClassify(info){

        var htmlTemp = ``;


        for(var i=0;i<info.length;i++){


            htmlTemp += `<div class=\"item\"> ${info[i].name}  </div>`

        }

        $(".classify").empty().append(htmlTemp);

    }




    /*
     * @Description 加载标签
     * @Author 284668461@qq.com
     * @Date 15:55 2020/4/26
     * @Param
     * @return
     **/
    function toLoadLabel(){
        $.post({
            url:"/blog/getTag",
            success:(data)=>{

                LoadLabel(JSON.parse(data));
            }
        });
    }


    /*
     * @Description 显示标签到界面
     * @Author 284668461@qq.com
     * @Date 15:54 2020/4/26
     * @Param
     * @return
     **/
    function LoadLabel(info){


        var blogListLabelHtmlTemp = ``;
        var publishLabelHtmlTemp="";
        LabelHtmlTemp = ``;


        for(var i=0;i<info.length;i++){

            blogListLabelHtmlTemp += `<div class="item" data-value=" ${info[i].name}"> ${info[i].name}  </div>`;
            // publishLabelHtmlTemp += ` <option value="${info[i].name}">${info[i].name}</option>`;
            publishLabelHtmlTemp += `<div class="item" data-value=" ${info[i].name} ">${info[i].name} </div>`;
        }

        $("#blogListLabel").empty().append(blogListLabelHtmlTemp);
        $("#publishLabel").empty().append(blogListLabelHtmlTemp);


        $('#publishLabel').dropdown();


    }




    //发布按钮点击事件
    $("#publishConfirm").on("click",()=>{


        console.log("点击事件");


    //    获得输入信息
        var title = $("#blogTitle");
        var original = $("#blogOriginal>div.active");
        var classify = $("#blogClassify>div.active");
        var label = $("#publishLabel>div.active");
        var coverImg = $("#fileUpload");
        var publishBody = $("#blogBody");

        var blogIsDraft = $("#blogIsDraft").is(':checked');
        var blogIsComment = $("#blogIsComment").is(':checked');
        var blogIsAdmire = $("#blogIsAdmire").is(':checked');



        console.log(title.val());
        console.log($( original[0] ).attr("data-value"));
        console.log($( classify[0] ).text());
        console.log($(label).text());
        console.log($(coverImg).prop('files'));//获取到文件列表);
        console.log(publishBody.val());
        console.log(blogIsDraft);
        console.log(blogIsComment);
        console.log(blogIsAdmire);


    //    验证信息完整性

        if(title.val().length<=0){

            Toast("请输入标题");
            return;
        }

    //    发送请求


        var formData = new FormData();
        formData.append('file',  $(coverImg).prop('files')[0] );
        formData.append('title',  title.val() );

        if( $( original[0] ).attr("data-value") != undefined){

            formData.append('original',  $( original[0] ).attr("data-value") );
        }else{

            formData.append('original',  "原创" );
        }


        formData.append('classify',  $( classify[0] ).text() );
        formData.append('tab',  $(label).text() );
        formData.append('body',  publishBody.val() );
        formData.append('blogIsDraft',  blogIsDraft );
        formData.append('blogIsComment',  blogIsComment );
        formData.append('blogIsAdmire',  blogIsAdmire );




        $.post({
            url:"/admin/insertBlog",
            data: formData,
            processData: false,
            contentType: false,

            success:(data)=>{

                // LoadLabel(JSON.parse(data));

                console.log(data);
            }
        });


    })



// 发布 end  ---------------------------





});
