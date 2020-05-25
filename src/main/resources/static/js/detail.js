$(() => {


    setTimeout(() => {
        $("#loading").hide();
    }, 1200);

    $(window).on('unload', function () {
        $(window).scrollTop(0);
    });

    $("#sidebar").on("click", () => {

        $('.m-item').toggleClass("m-mobile-hide");
    });


    let blog = new Vue({
        el: "#center",
        data: {
            blogInfo: {}, //博客信息
            blogTag: [], //博客标签
            blogId: "", //当前所在页博客id
            comment: [], //评论列表
            copyright: {},   //版权信息
            admireFlag: false, //赞赏状态
            admireInfoFlag: false, //赞赏信息
            defaultCommentBody: "请输入评论信息...",    //评论框默认提示
            commentFlag: true,
            commentBody: "", // 评论内容
            commentNickName: "", //评论昵称
            replyCommentId: 0 // 回复评论id
        },
        created: function () {
            this.blogId = this.getBlogId();
            this.toLoadBlogDetail();
        },
        methods: {
            //获得url传递参数
            getBlogId: function (name = "id") {

                var query = window.location.search.substring(1);
                var vars = query.split("&");
                for (var i = 0; i < vars.length; i++) {
                    var pair = vars[i].split("=");
                    if (pair[0] === name) {
                        return pair[1];
                    }
                }

            },
            //加载博文详情
            toLoadBlogDetail: function () {
                $.get({
                    url: "/blog/getBlogDetail",
                    data: {
                        id: this.blogId
                    },
                    success: (data) => {
                        var info = JSON.parse(data);

                        if (info["copyright"]["path"] === "") {
                            info["copyright"]["path"] = window.location.href;
                        }


                        //若评论数和查看人数为空则替换为0
                        if (info["blogDetail"].comment_num === null) {
                            info["blogDetail"].comment_num = 0;
                        }
                        if (info["blogDetail"].visitor_num === null) {
                            info["blogDetail"].visitor_num = 0;
                        }


                        if ((info["copyright"]["copyrightAuthor"] === "") && (info["copyright"]["copyrightFlag"] !== "转载")) {

                            info["copyright"]["copyrightAuthor"] = info["blogDetail"]["NAME"];

                        }


                        this.blogTag = info["blogTag"];
                        this.blogInfo = info["blogDetail"];
                        this.comment = info["comment"];
                        this.copyright = info["copyright"];

                        if (info["blogDetail"]["Comment_flag"] === 1) {
                            this.commentFlag = true;
                        } else {
                            this.commentFlag = false;
                        }


                    }

                });
            },
            //加载博客评论
            toLoadBlogComment: function () {

                $.post({
                    url: "/blog/getBlogComment",
                    data: {
                        blogId: this.blogId
                    },
                    success: (data) => {
                        var info = JSON.parse(data);
                        this.comment = info;
                    }

                });
            },

            //赞赏按钮点击事件
            admireClick: function () {
                this.admireInfoFlag = !this.admireInfoFlag;
            },

            //发布评论按钮点击事件
            commentAddClick: function () {

                if (this.commentBody === "") {
                    return Toast("请输入评论");
                }


                if (this.commentBody.length > 1000) {
                    return Toast("您评论的字数太长啦！请保持在一千字以内");
                }


                $.post({
                    url: "/blog/insertBlogComment",
                    data: {
                        nickName: this.commentNickName,
                        commentBody: this.commentBody,
                        blogId: this.blogId,
                        replyCommentId: this.replyCommentId
                    },
                    success: (data) => {

                        if (data) {

                            this.commentBody = "";
                            this.commentNickName = "";
                            Toast("发表评论成功");

                            this.toLoadBlogComment();

                        }

                    }

                });


            },
            //清除评论点击事件
            commentClearClick: function () {

                this.commentBody = "";
                this.commentNickName = "";

            },
            //回复评论点击事件
            replyClick: function (id, nickname) {

                this.defaultCommentBody = "@" + nickname;
                this.commentBody = "";
                this.replyCommentId = id;

                var h = $(document).height() - $(window).height();
                $(document).scrollTop(h);

            }

        }

    });

});
