package com.blog.service;

import com.blog.MyBatisUtil;
import com.blog.dao.UserMapper;
import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/*
 * @Description 用户实现类
 * @Author 284668461@qq.com
 * @Date 15:22 2020/4/21
 **/
@Configuration
public class UserServiceImp implements UserService{


    /*
     * @Description 用户登录实现方法
     * @Author 284668461@qq.com
     * @Date 15:43 2020/4/21
     * @Param [user, pass]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean login(String user, String pass) {


        SqlSession session =  MyBatisUtil.getSessionFactory();


        UserMapper um = session.getMapper(UserMapper.class);

       Map ls =  um.Login(user,pass);

        if((ls != null)&&(ls.size()>0)){
            return  true;
        }

        return false;
    }
}
