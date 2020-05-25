$(() => {


    setTimeout(() => {
        $("#loading").hide();
    }, 1200);
    // 解决浏览器记录刷新前滚动位置并跳转到滚动位置的不优雅操作
    $(window).on('unload', function () {
        $(window).scrollTop(0);
    });

    //导航条 start---------------------------
    $("#sidebar").on("click", () => {
        $('.m-item').toggleClass("m-mobile-hide");
    });


    //切换 section
    $("#nav a.item").click(function () {

        let thisLabel = $(this).attr("data-section");

        $(this).addClass("active").siblings().removeClass("active");

        $(`section.${thisLabel}`).show(300).siblings().hide(300);
    });


    let loginContainer = new Vue({
        el: "#loginContainer",
        data: {
            userName: "",
            userIcon: "",
            loginFlag: false
        },
        created: function () {
            this.queryLoginState();
        },
        methods: {
            //查询登录状态
            queryLoginState: function () {

                $.post({
                    url: "/user/queryLoginState",
                    success: (data) => {

                        if (data) {
                            this.loginFlag = true;
                            this.toLoadUserInfo();
                        } else {
                            this.loginFlag = false;
                            this.cookieLogin();
                        }
                    }
                });

            },
            //cookie自动登录
            cookieLogin: function () {

                $.post({
                    url: "/user/cookieLogin",
                    success: (data) => {

                        if (data) {
                            this.loginFlag = true;
                            this.toLoadUserInfo();
                        } else {
                            this.loginFlag = false;
                        }
                    }
                });

            },
            //加载用户信息
            toLoadUserInfo: function () {

                $.post({
                    url: "/user/getLoginInfo",
                    success: (data) => {

                        if (data.length > 1) {
                            var info = JSON.parse(data);
                            this.userName = info["name"];
                            this.userIcon = info["user_icon"];


                            //初始化 下拉
                            $(".ui.dropdown").dropdown();

                        } else {
                            this.loginFlag = false;
                        }
                    }
                });
            },
            //登录按钮点击事件
            loginBtn: function () {

                $('#login').modal('show');
            },
            //退出登录
            loginOut: function () {
                $.post({
                    url: "user/loginOut",
                    success: (data) => {
                        if (data) {
                            this.loginFlag = false;
                        }
                    }
                });

            }
        }
    });


    let login = new Vue({
        el: "#login",
        data: {
            user: "",
            pass: "",
            rememberPassFlag: "false"
        },
        methods: {

            btnFun: function () {

                if ((this.user.length <= 0) || (this.pass.length <= 0)) {
                    Toast("请输入账户或密码");
                    return;
                }


                $("#loading").show();

                axios.post(
                    '/user/login',
                    {
                        user: this.user,
                        pass: hex_md5(encodeURIComponent(this.pass)),
                        rememberPassFlag: this.rememberPassFlag,
                    })
                    .then((res) => {


                        $("#loading").hide();


                        if (res.data) {
                            // window.location.href = "admin.html";
                            $('#login').modal('hide');
                            loginContainer.loginFlag = true;
                            loginContainer.toLoadUserInfo();
                        } else {
                            Toast("登录失败，账户或密码错误");
                        }
                    })
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });
            }
        }
    });


//导航条 end---------------------------


//home  start   ---------------------------

    let home = new Vue({
        el: "#home",
        data: {
            homeBlogList: [],
            hotBlogList: [],
            tabCloud: [],
            blogPage: 0,
            thisPage: 0,
            prePageBtn: 'disabled',
            nextPageBtn: false
        },
        created: function () {

            this.loadHomeBlog();
            this.loadHomeHot();
            this.loadTagCloud();

        },
        methods: {
            //加载首页博文列表函数
            loadHomeBlog: function (page = 0) {

                $.post({
                    url: "/blog/getBlogByPage",
                    data: {
                        page: page
                    },
                    success: (data) => {

                        var info = JSON.parse(data);
                        var blogList = info["blogList"];
                        var pageInfo = info["pageInfo"];

                        blogList.map((item, index, arr) => {

                            //若评论数和查看人数为空则替换为0
                            if (item.comment_num === null) {
                                item.comment_num = 0;
                            }
                            if (item.visitor_num === null) {
                                item.visitor_num = 0;
                            }
                        });

                        this.homeBlogList = blogList;
                        this.blogPage = pageInfo["blogPage"];
                        this.initializePage();
                    }
                });
            },
            //加载热文
            loadHomeHot: function () {

                $.post({
                    url: "/blog/getBlogByHot",
                    success: (data) => {

                        var info = JSON.parse(data);

                        info.map((item, index, arr) => {

                            //若评论数和查看人数为空则替换为0
                            if (item.comment_num === null) {
                                item.comment_num = 0;
                            }
                            if (item.visitor_num === null) {
                                item.visitor_num = 0;
                            }

                        });

                        this.hotBlogList = info;
                    }
                });

            },
            //加载词云
            loadTagCloud: function () {

                $.post({
                    url: "/blog/getTag",
                    success: (data) => {

                        var info = JSON.parse(data);

                        var wordTemp = [];

                        info.map((item) => {
                            wordTemp.push([item.name, parseInt(Math.random() * 100)]);
                        });

                        var canvas = document.getElementById("tabCloudCanvas");
                        var options = eval({
                            "list": wordTemp,
                            "gridSize": 25, // 密集程度 数字越小越密集
                            "fontWeight": 'normal',
                            "fontFamily": 'Times, serif',
                            "color": 'random-light',
                            "backgroundColor": 'white',
                            "maxFontSize": 15,
                            "minFontSize": 6,
                            "rotateRatio": 0.1
                        });

                        //生成词云
                        WordCloud(canvas, options);
                        this.tabCloud = info;
                    }
                });
            },

            //上一页
            prePage: function () {

                this.thisPage--;

                if (this.thisPage <= 0) {
                    this.prePageBtn = 'disabled';
                    this.nextPageBtn = false;
                }

                if (this.thisPage > 0) {
                    this.prePageBtn = false;
                }

                this.loadHomeBlog(this.thisPage);


            },
            //下一页
            nextPage: function () {

                this.thisPage++;

                if (this.thisPage >= this.blogPage - 1) {
                    this.nextPageBtn = 'disabled';
                    this.prePageBtn = false;
                }

                if (this.thisPage + 1 > 0) {
                    this.prePageBtn = false;
                }


                this.loadHomeBlog(this.thisPage);
            },
            //初始化页码
            initializePage: function () {


                if ((this.thisPage === (this.blogPage - 1)) && (this.blogPage !== 0)) {
                    this.nextPageBtn = 'disabled';
                }

            }

        }

    });


//home  end     ---------------------------


//classify   start     -------------------------

    let classify = new Vue({
        el: "#classify",
        data: {
            classifyOption: [],
            classifyBlog: []
        },
        created: function () {

            this.toLoadClassify();

        },
        methods: {
            //加载分类
            toLoadClassify: function () {

                $.post({
                    url: "/blog/getClassify",
                    success: (data) => {

                        var info = JSON.parse(data);
                        //若分类数量为空则替换为0
                        info.map((item) => {

                            if (item.classifynum === null) {
                                item.classifynum = 0;
                            }
                        });

                        this.classifyOption = info;
                        this.switchClassifyOption();
                    }
                });

            },

            //按分类加载博文
            toclassifyBlog: function (classifyId) {

                $.post({
                    url: "/blog/getBlogByClassify",
                    data: {
                        classifyid: classifyId
                    },
                    success: (data) => {

                        var info = JSON.parse(data);

                        info.map((item, index, arr) => {

                            //若评论数和查看人数为空则替换为0
                            if (item.comment_num === null) {
                                item.comment_num = 0;
                            }
                            if (item.visitor_num === null) {
                                item.visitor_num = 0;
                            }

                        });
                        this.classifyBlog = info;
                    }
                });
            },

            //切换分类选项
            switchClassifyOption: function (id = this.classifyOption[0].id) {

                var info = this.classifyOption;
                info.map((item) => {
                    //给json 添加一项选中标记
                    // 若循环 id 等于传入的点击id ,则替换为已选中
                    if (item.id === id) {
                        item.isSelected = "teal";
                    } else {
                        item.isSelected = "black";
                    }

                    //当 num 为空时 替换为 0
                    if (item.num === null) {
                        item.num = 0;
                        return item;
                    }
                });

                this.classifyOption = info;
                this.toclassifyBlog(id);

            },

            //分类点击事件
            btnClick: function (id, classifynum) {


                if (classifynum < 1) {
                    Toast("该分类没有博文，请选择其他分类");
                    return;
                }

                this.switchClassifyOption(id);
                this.toclassifyBlog(id);

            }
        }
    });


//classify   end     ---------------------------

//tag   start     ---------------------------

    let tag = new Vue({
        el: "#tag",
        data: {
            tabOption: [],
            tabBlog: [],
        },
        created: function () {
            this.toLoadTag();
        },
        methods: {
            //加载标签
            toLoadTag: function () {

                $.post({
                    url: "/blog/getTag",
                    success: (data) => {
                        this.tabOption = JSON.parse(data);
                        this.switchTagOption();
                    }
                });
            },
            //按标签加载博文
            toLoadTagBlog: function (tagId) {

                $.post({
                    url: "/blog/getBlogByTag",
                    data: {
                        tagId: tagId
                    },
                    success: (data) => {

                        var info = JSON.parse(data);

                        info.map((item) => {

                            //若评论数和查看人数为空则替换为0
                            if (item.comment_num === null) {
                                item.comment_num = 0;
                            }
                            if (item.visitor_num === null) {
                                item.visitor_num = 0;
                            }

                        });
                        this.tabBlog = info;
                    }
                });
            },
            //切换标签
            switchTagOption: function (id = this.tabOption[0].id) {

                var info = this.tabOption;
                info.map((item) => {

                    //给json 添加一项选中标记
                    // 若循环 id 等于传入的点击id ,则替换为已选中
                    if (item.id === id) {
                        item.isSelected = "teal";
                    } else {
                        item.isSelected = "black";
                    }
                });

                this.tagOption = info;
                this.toLoadTagBlog(id);
            },
            //标签点击事件
            tabClick: function (id) {
                this.switchTagOption(id);
                this.toLoadTagBlog(id);
            }
        }
    });
//tag     end     ---------------------------


//timeLine     start   ---------------------------
    let timeLine = new Vue({
        el: "#timeLine",
        data: {
            timeLineInfo: []
        },
        created: function () {
            this.toLoadTimeLine();
        },
        methods: {
            toLoadTimeLine: function () {
                $.post({
                    url: "/blog/getTimeLine",
                    success: (data) => {
                        this.timeLineInfo = JSON.parse(data);
                    }
                });
            }
        }
    });

//timeLine     end     ---------------------------

});









