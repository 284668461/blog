package com.blog.service;

import com.blog.MyBatisUtil;
import com.blog.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
 * @Description 用户实现类
 * @Author 284668461@qq.com
 * @Date 15:22 2020/4/21
 **/
@Service
public class UserServiceImp implements UserService {


    /*
     * @Description 用户登录实现方法
     * @Author 284668461@qq.com
     * @Date 15:43 2020/4/21
     * @Param [user, pass]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean login(String user, String pass) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        UserMapper um = session.getMapper(UserMapper.class);

        Map m = um.Login(user, pass);

        if ((m != null) && (m.size() > 0)) {
            return true;
        }

        return false;
    }

    @Override
    public Map getUserInfo(String user) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        UserMapper um = session.getMapper(UserMapper.class);

        return um.getUserInfo(user);

    }
}
