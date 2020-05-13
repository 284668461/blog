$(()=>{

    $("#sidebar").on("click",()=>{

        $('.m-item').toggleClass("m-mobile-hide");
    });




    let blog = new Vue({
        el:"#center",
        data:{
            blogInfo:{},
            blogTag:[],
            comment:[],
            copyright:{},
            admireFlag:false
        },
        created:function(){
            this.toLoadBlogDetail();
        },
        methods: {
            //获得url传递参数
            getBlogId: function (name="id") {
                console.log("GetRequest");

                var query = window.location.search.substring(1);
                var vars = query.split("&");
                for (var i=0;i<vars.length;i++) {
                    var pair = vars[i].split("=");
                    if(pair[0] === name){
                        return pair[1];
                    }
                }

            },
            //加载博文详情
            toLoadBlogDetail: function () {
                var id = this.getBlogId();
                $.get({
                    url: "/blog/getBlogDetail",
                    data:{
                        id,id
                    },
                    success: (data) => {
                        var info = JSON.parse(data);

                        this.blogTag = info["blogTag"];
                        this.blogInfo = info["blogDetail"];
                        this.comment = info["comment"];
                        this.copyright = info["copyright"];


                        if(this.copyright["path"] === null){
                            this.copyright["path"] = window.location.href;
                        }


                        console.log(this.blogInfo);
                        console.log(this.comment);
                        console.log(this.copyright);



                        //
                        // info.map((item, index, arr) => {
                        //
                        //     //若评论数和查看人数为空则替换为0
                        //     if (item.comment_num === null) {
                        //         item.comment_num = 0;
                        //     }
                        //     if (item.visitor_num === null) {
                        //         item.visitor_num = 0;
                        //     }
                        // });

                        // this.homeBlogList = info;


                    }

                });
            },
            //渲染博文信息到界面
            loadBlogDetail: function () {

            },

            admireClick:function(){
                this.admireFlag = !this.admireFlag;
                console.log("dianji");
            }

        }

    });

});
