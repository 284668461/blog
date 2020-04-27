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



        switch (thisLabel) {

            case "index":
                // load().loadTag();
                break;

            case "classify":

                classify.toLoadClassify();
                break;
            case "label":
                tab.toLoadTag();

                break;

            case "timeline":
                // loadTag();
                break;

        }

    });

//导航条 end---------------------------


//登录 start ---------------------------

    $("#loginBtn").on("click",()=>{

        $('#login').modal('show');

    });


    let login = new Vue({
        el:"#login",
        data:{
            user:"",
            pass:"",
            rememberPassFlag:"false"

        },
        methods:{

            btnFun:function(){

                if((this.user.length<=0)||(this.pass.length<=0)){
                    Toast("请输入账户或密码");

                    return;
                }


                axios.post(
                    '/user/login',
                    {
                        user: this.user,
                        pass: this.pass,
                        rememberPassFlag: this.rememberPassFlag,
                    })
                    .then((res)=>{

                        if(res.data === false){
                            Toast("登录失败，账户或密码错误");
                        }else{
                            window.location.href = "admin.html";
                        }

                    })
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });

            }
        }
    });




//登录 end   ---------------------------








//home  start   ---------------------------




//home  end     ---------------------------



//classify   start     -------------------------

    var classify = new Vue({
        el:"#classify",
        data:{
            classifyOption:[],
            classifyBlog:[],

        },
        methods:{

            toLoadClassify:function(){

                $.post({
                    url:"/blog/getClassify",
                    success:(data)=>{
                        this.loadClassifyOption(JSON.parse(data));
                    }
                });

            },

            toclassifyBlog:function(classifyId){

                $.post({
                    url:"/blog/getBlogByClassify",
                    data:{
                        classifyid:classifyId
                    },
                    success:(data)=>{
                        console.log(data);

                    }
                });
            },


            //分类选项
            loadClassifyOption:function(info,id=info[0].id){
                info.map((item,index,arr)=>{

                    //给json 添加一项选中标记
                    // 若循环 id 等于传入的点击id ,则替换为已选中
                    if(item.id === id){
                        item.isSelected = "teal";
                    }else{
                        item.isSelected = "black";
                    }

                    //当 num 为空时 替换为 0
                    if(item.num === null){
                        item.num = 0;
                        return item ;
                    }
                });

                this.classifyOption = info;
                this.toclassifyBlog(id);
            },

            btnClick:function(id){

                var temp = [{"del_flag":"0","blog_id":1,"type_id":1,"num":2,"name":"技术文章","id":1,"time":1587784048000},{"del_flag":"0","blog_id":null,"type_id":null,"num":null,"name":"心情随笔","id":2,"time":1587784061000},{"del_flag":"0","blog_id":null,"type_id":null,"num":null,"name":"生活感悟","id":3,"time":1587802836000}]

                this.loadClassifyOption(temp,id);
            }
        }
    });



//classify   end     ---------------------------

//tab   start     ---------------------------

    var tab = new Vue({
        el:"#tab",
        data:{
            tabInfo:[]
        },
        methods:{
            toLoadTag:function(){

                $.post({
                    url:"/blog/getTag",
                    success:(data)=>{
                        //通知vue 渲染界面
                        tab.loadTabOption(JSON.parse(data));
                    }
                });


            },
            loadTabOption:function(info,id=info[0].id){

                info.map((item,index,arr)=>{

                    //给json 添加一项选中标记
                    // 若循环 id 等于传入的点击id ,则替换为已选中
                    if(item.id === id){
                        item.isSelected = "teal";
                    }else{
                        item.isSelected = "black";
                    }

                    //当 num 为空时 替换为 0
                    if(item.num === null){
                        item.num = 0;
                        return item ;
                    }
                });

                this.tabInfo = info;
            },
            tabClick: function(id){

                var temp = [{"name":"web","id":3,"time":1587781812000},{"name":"jquery","id":4,"time":1587781822000},{"name":"js","id":5,"time":1587781851000},{"name":"mysql","id":6,"time":1587781848000}]

                this.loadTabOption(temp,id);
            }
        }
    });
//tab     end     ---------------------------









});









