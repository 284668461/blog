package com.blog.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @Description 用户
 * @Author 284668461@qq.com
 * @Date 16:24 2020/4/21
 **/
@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    /*
     * @Description 用户登录
     * @Author 284668461@qq.com
     * @Date 16:20 2020/4/21
     * @Param [user, pass, rememberPassFlag, session, res, attributes]
     * @return java.lang.String
     **/
    @PostMapping(value = "login")
    @ResponseBody
    public String loginHandler(
            @RequestBody JSONObject json,
            HttpSession session,
            HttpServletResponse res,
            RedirectAttributes attributes
    ){


        String user = json.getString("user");
        String pass = json.getString("user");
        String rememberPassFlag = json.getString("rememberPassFlag");


        System.out.println(json);
        System.out.println(user);
        System.out.println(pass);


        Boolean flag =  userService.login(user,pass);

        if(flag){

            session.setAttribute("user",user);

//          记住密码
            if(Boolean.parseBoolean(rememberPassFlag)){

                // 设置cookie 过期时间 7 天
                Cookie cookie = new Cookie("user",user);

                cookie.setMaxAge(60*24*7);
                res.addCookie( cookie );
            }


            return "true";
        }


        // 发生消息 到前端
//        attributes.addFlashAttribute("mesg","登录失败，用户名和密码错误");

        return "false";

    }



    /*
     * @Description 注销
     * @Author 284668461@qq.com
     * @Date 16:24 2020/4/21
     * @Param [session]
     * @return java.lang.String
     **/
    @PostMapping("loginOut")
    public String loginOut( HttpSession session){


        session.removeAttribute("user");


        return "redirect:index" ;

    }





}
