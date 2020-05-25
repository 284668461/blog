package com.blog.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blog.other.Tool;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.Map;

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


    @Autowired
    private Tool tool;


    /*
     * @Description 用户登录
     * @Author 284668461@qq.com
     * @Date 16:20 2020/4/21
     * @Param [user, pass, rememberPassFlag, session, res, attributes]
     * @return java.lang.String
     **/
    @PostMapping(value = "login")
    @ResponseBody
    public Boolean loginHandler(
            @RequestBody JSONObject json,
            HttpSession session,
            HttpServletResponse res,
            RedirectAttributes attributes) {


        String user = json.getString("user");
        String pass = json.getString("pass");
        String rememberPassFlag = json.getString("rememberPassFlag");


        System.out.println("获得的 pass");
        System.out.println(pass);

        Boolean flag = userService.login(user, pass);

        if (flag) {

            session.setAttribute("user", user);
//          记住密码
            if (Boolean.parseBoolean(rememberPassFlag)) {

                // 设置cookie 过期时间 7 天
                Cookie cookie = new Cookie("user", user);
                cookie.setMaxAge(60 * 24 * 7);
                res.addCookie(cookie);

                Cookie cookie2 = null;
                try {
                    cookie2 = new Cookie("pass", tool.getMD5Code(pass));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cookie2.setMaxAge(60 * 24 * 7);
                res.addCookie(cookie);

            }


            return true;
        }

        return false;

    }


    /*
     * @Description 注销
     * @Author 284668461@qq.com
     * @Date 16:24 2020/4/21
     * @Param [session]
     * @return java.lang.String
     **/
    @PostMapping("loginOut")
    @ResponseBody
    public Boolean loginOut(HttpSession session, HttpServletResponse res) {


        session.removeAttribute("user");

        if (session.getAttribute("user") == null) {

            return true;
        } else {

            return false;
        }


    }


    /*
     * @Description 获得当前登录用户信息
     * @Author 284668461@qq.com
     * @Date 20:11 2020/5/16
     * @Param []
     * @return java.lang.String
     **/
    @PostMapping("getLoginInfo")
    @ResponseBody
    public String getLogInfo(HttpSession session) {

        String user = (String) session.getAttribute("user");

        if (user != null) {
            return JSON.toJSONString(userService.getUserInfo(user));
        } else {
            return null;
        }
    }


    /*
     * @Description 查询登录状态
     * @Author 284668461@qq.com
     * @Date 8:32 2020/5/17
     * @Param [req, cookie]
     * @return java.lang.Boolean
     **/
    @PostMapping("queryLoginState")
    @ResponseBody
    public Boolean queryLoginState(HttpSession session) {

        String user = (String) session.getAttribute("user");

        if (user != null) {
            return true;
        } else {
            return false;
        }

    }


    /*
     * @Description cookie自动登录
     * @Author 284668461@qq.com
     * @Date 8:31 2020/5/17
     * @Param [req]
     * @return java.lang.Boolean
     **/
    @PostMapping("cookieLogin")
    @ResponseBody
    public Boolean cookieLogin(HttpServletRequest req) {

        String user = null, pass = null;
        // 获取cookie
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                user = cookie.getValue();
            }
            if (cookie.getName().equals("pass")) {
                pass = cookie.getValue();
            }
        }
        if (userService.login(user, pass)) {
            return true;
        } else {
            return false;
        }
    }


}