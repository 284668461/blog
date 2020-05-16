package com.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper {



    /*
     * @Description 登录
     * @Author 284668461@qq.com
     * @Date 15:27 2020/4/21
     * @Param [user, pass]
     * @return java.util.List
     **/
    @Select("select * from b_user where user = #{user} and pass = #{pass}")
    Map Login(String user, String pass);



    @Select("select id,user,name,user_icon from b_user where user = #{user}")
    Map getUserInfo(String user);


}
