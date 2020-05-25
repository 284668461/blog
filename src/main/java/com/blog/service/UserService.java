package com.blog.service;

/*
 * @Description 用户接口
 * @Author 284668461@qq.com
 * @Date 15:23 2020/4/21
 **/


import java.util.Map;

public interface UserService {


    /*
     * @Description  用户登录
     * @Author 284668461@qq.com
     * @Date 15:22 2020/4/21
     * @Param [user, pass]
     * @return java.lang.String
     **/

    Boolean login(String user, String pass);


    /*
     * @Description 获得用户信息
     * @Author 284668461@qq.com
     * @Date 20:15 2020/5/16
     * @Param [user]
     * @return java.util.Map
     **/
    Map getUserInfo(String user);


}
