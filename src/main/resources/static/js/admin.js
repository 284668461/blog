$(()=>{

    $(window).on('unload', function() { $(window).scrollTop(0); });

    //初始化
    initializeDropdown();
    toLoadUserInfo();
    toLoadClassify();
    toLoadLabel();
    toloadBlog();


    //初始化mk编辑器
    let  iEditormd = editormd("iEditormd",{

        width:"100%",
        height:640,
        syncScrolling: "single",
        path:"lib/editor/lib/",
        saveHTMLToTextarea : true,

        /**上传图片相关配置如下*/
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/admin/uploadImg",//注意你后端的上传图片服务地址

    });



//    tab start

    $("#tab>a.item").on("click",function(){


        var clickTab = $(this).attr("data-tab");

        $(this).addClass("active").siblings().removeClass("active");

        $("#"+clickTab).removeClass("ihide").siblings("section").addClass("ihide");

    });

//    tab end






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







// 博客 start  -------------------------

//    删除博客按钮点击事件
    $("body").on("click",".delBlog",function(item){

        var that = $("this");

        var blogid = $(this).data("blogid");


        $('#hint').modal({
            closable : false,
            onDeny   : function(){

            },
            onApprove: function() {

                $.post({
                    url:"/admin/delBlog",
                    data:{
                        blogId:blogid
                    },
                    success:(data)=>{

                        if(data){
                            Toast("删除博客成功");
                            toloadBlog();
                        }
                    }
                });
            }
        }).modal('show');

    });





// 博客 end  -------------------------








// 发布 start  -------------------------

    //加载用户信息
    function toLoadUserInfo(){
        $.post({
            url:"/user/getLoginInfo",
            success:(data)=>{

                if(data.length<1){
                    location.replace("index.html");
                }else{
                    var info = JSON.parse(data);

                    $("#userIcon").attr("src",info["user_icon"]);
                    $("#userName").text(info["name"]);
                }
            }
        });
    }


    //加载博客
    function toloadBlog(){
        $.post({
            url:"/blog/getAllBlog",
            success:(data)=>{

                var info = JSON.parse(data);

                info.map((item,index,arr)=>{

                    //若评论数和查看人数为空则替换为0
                    if(item.comment_num === null){
                        item.comment_num = 0;
                    }
                    if(item.visitor_num === null){
                        item.visitor_num = 0;
                    }
                });


                loadBlog(info);
            }
        });

    }


    //渲染博客信息
    function loadBlog(blogInfo){

        var temp = "";
        for(var i=0;i<blogInfo.length;i++){

            temp += `<tr>
                            <td>
                                <div class="ui segment blogItem">
                                    <div class="ui  grid stackable  mobile reversed">
                                        <div class="ui eleven wide column iRelative">
                                            <h3 class="ui header"><a href="/detail/${blogInfo[i].blog_id}">${blogInfo[i].title}</a></h3>
                                            <p class="iBlogBreviary">
                                              ${blogInfo[i].blog_intro}
                                            </p>
                                            <div class="ui grid iInBottom">
                                                <div class="eleven wide column">
                                                    <div class="ui mini horizontal link list">
                                                        <div class="item">
                                                            <i class="clock outline icon"></i><span>${blogInfo[i].publish_date}</span>
                                                        </div>
                                                        <div class="item">
                                                            <i class="eye icon"></i> <span>${blogInfo[i].visitor_num}</span>
                                                        </div>
                                                        <div class="item">
                                                            <i class="comment alternate icon"></i> <span>${blogInfo[i].comment_num}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="right aligned five wide column">
                                                    <a class="ui blue basic label">${blogInfo[i].classify_name}</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="ui five wide column">
                                            <a href="">
                                                <img alt="" class="ui rounded image iMarginTop iBolgListImg" src="${blogInfo[i].cover_img_path}">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="extra content">
                                    <div class="ui two buttons">
                                       
                                        <div class="ui button delBlog" data-blogId="${blogInfo[i].blogid}">
                                            <i class="ui icon trash alternate outline"></i>
                                        </div>
                                        
                                     
                                    </div>
                                </div>
                            </td>
                        </tr>`;

        }


    // <div class="ui basic green button editBlog" data-blogId="${blogInfo[i].blogid}">
    //         <i class="icon pencil alternate"></i>
    //         </div>


        $("#blogListBody").empty().append(temp);


    }





    /*
     * @Description 初始化下拉菜单
     * @Author 284668461@qq.com
     * @Date 21:15 2020/5/17
     * @Param
     * @return
     **/
    function initializeDropdown(){
        $(".ui.dropdown").dropdown();
        $("#titleDropDown").dropdown({
            onChange:function(item){
                console.log(item);
                if(item !== "原创"){
                    $("#hintCopyright").slideDown();
                }else{
                    $("#hintCopyright").slideUp();
                }
            }
        });

    }


    /*
     * @Description 加载分类
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
     * @Description 渲染分类到界面
     * @Author 284668461@qq.com
     * @Date 15:55 2020/4/26
     * @Param
     * @return
     **/
    function loadClassify(info){

        var htmlTemp = ``;


        for(var i=0;i<info.length;i++){

            htmlTemp += `<div class=\"item\" data-id=" ${info[i].id}"> ${info[i].name}  </div>`
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

            blogListLabelHtmlTemp += `<div class="item" data-value=" ${info[i].name}" data-id=" ${info[i].id}"> ${info[i].name}  </div>`;
            // publishLabelHtmlTemp += ` <option value="${info[i].name}">${info[i].name}</option>`;
            publishLabelHtmlTemp += `<div class="item" data-value=" ${info[i].name} " data-id=" ${info[i].id}" >${info[i].name} </div>`;
        }

        $("#blogListLabel").empty().append(blogListLabelHtmlTemp);
        $("#publishLabel").empty().append(blogListLabelHtmlTemp);


        initializeDropdown();


    }




    /*
     * @Description 查询博客
     * @Author 284668461@qq.com
     * @Date 10:36 2020/5/5
     * @Param
     * @return
     **/
    function toLoadBlogByMixtureQuery(tagId = 0,classifyId = 0,title){

        $.post({
            url:"/blog/getBlogByMixtureQuery",
            data:{
                tagId:tagId,
                classifyId:classifyId,
                title:title
            },
            success:(data)=>{

                loadBlog(JSON.parse(data));
            }
        });

    }



    //注销按钮点击事件
    $("#loginOut").click(function() {

        $.post({
            url: "user/loginOut",
            success:(data)=> {
                if (data) {
                    location.replace("index.html");
                }
            }
        });
    });

    //搜索博客点击事件
    $("#searchBlog").on("click",()=>{


        var title  = $("#searchInput");
        var tagId  = $($("#blogListLabel>div.active")[0]).data("id");
        var classifyId  = $($("#searchClassify>div.active")[0]).data("id");


        if( (title.val()<1)&&(typeof(tagId) === 'undefined')&&(typeof(classifyId) === 'undefined')){

            Toast("请输入关键字，或选择标签，分类");
            return ;
        }

        toLoadBlogByMixtureQuery(tagId,classifyId,title.val());

    });


    //新增分类点击事件
    $("#addClassify").on("click",()=>{

        $('#hint_addClassify').modal({
            closable : false,
            onDeny   : function(){
            },
            onApprove: function() {

                var input = $("#addClassifyInput");


                if(input.val().length<1){
                    Toast("请输入分类名称");
                    return false;
                }


                $.post({
                    url:"/admin/queryClassify",
                    data:{classify:input.val()},
                    success:(data)=>{

                        if(data){

                            $.post({
                                url:"/admin/insertClassify",
                                data:{classify:input.val()},
                                success:(data)=>{

                                    if(data){
                                        Toast("新增分类成功");
                                        toLoadClassify();

                                        input.val("");
                                    }else{
                                        Toast("新增分类失败，请稍后重试");
                                    }
                                }
                            });
                        }else{
                            Toast("该分类已存在");
                            return false;
                        }
                    }
                });
            }
        }).modal('show');

    });


    //新增标签点击事件
    $("#addTag").on("click",()=>{

        $('#hint_addTag').modal({
            closable : false,
            onDeny   : function(){

            },
            onApprove: function() {


                var input = $("#addTagInput");


                if(input.val().length<1){
                    Toast("请输入标签名称");
                    return false;
                }


                $.post({
                    url:"/admin/queryTag",
                    data:{Tag:input.val()},
                    success:(data)=>{

                        if(data){
                            $.post({
                                url:"/admin/insertTag",
                                data:{Tag:input.val()},
                                success:(data)=>{
                                    if(data){
                                        Toast("新增标签成功");
                                        toLoadLabel();
                                        input.val("");
                                    }else{
                                        Toast("新增标签失败，请稍后重试");
                                    }
                                }
                            });
                        }else{
                            Toast("该标签已存在");
                            return false;
                        }
                    }
                });
            }
        }).modal('show');


    });


    //发布按钮点击事件
    $("#publishConfirm").on("click",()=>{

        //    获得输入信息
        var title = $("#blogTitle");
        var original = $("#blogOriginal>div.active");
        var classify = $("#blogClassify>div.active");
        var tag = $("#publishLabel").children("div.active");
        var coverImg = $("#fileUpload");
        var publishBody = $("#blogBody");
        var blogIntro = $("#blogIntro");


        var author = $("#author");
        var path = $("#path");


        var blogIsDraft = $("#blogIsDraft").is(':checked');
        var blogIsComment = $("#blogIsComment").is(':checked');
        var blogIsAdmire = $("#blogIsAdmire").is(':checked');



        //将选中的标签保存到数组
        var tagArr = new Array();
        for (let i = 0; i < tag.length; i++) {
            tagArr.push( $(tag[i]).attr("data-id")  )
        }


        //    验证信息完整性
        if((original.val()!="原创") && (original.val()!= undefined)){


            if(author.val().length<1){
                Toast("请输入原作者名称");
                return;
            }

            if(path.val().length<1){
                Toast("请输入原文地址");
                return;
            }

        }




        if(title.val().length<=0){

            Toast("请输入标题");
            return;
        }

        if(blogIntro.val().length<=0){

            Toast("请输入简介");
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

        var classifyTemp = $( classify[0] ).text();

        if(classifyTemp.length<1){
            classifyTemp = "技术文章"
        }


        formData.append('classify',  classifyTemp );
        formData.append('tagArr',  tagArr );
        formData.append('body',  publishBody.val() );
        formData.append('blogIsDraft',  blogIsDraft );
        formData.append('blogIsComment',  blogIsComment );
        formData.append('blogIsAdmire',  blogIsAdmire );
        formData.append('author',  author.val() );
        formData.append('path',  path.val() );
        formData.append("blogIntro",  blogIntro.val() );


        $.post({
            url:"/admin/insertBlog",
            data: formData,
            processData: false,
            contentType: false,

            success:(data)=>{

                if(data){
                    Toast("发布博客成功");
                    location.replace(document.referrer);
                }
            }
        });
    })


// 发布 end  ---------------------------

});
