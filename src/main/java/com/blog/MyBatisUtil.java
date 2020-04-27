package com.blog;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.Reader;

/*
 * @Description mybatis  工具类
 * @Author 284668461@qq.com
 * @Date 16:15 2020/4/21
 **/
public class MyBatisUtil {


    private static SqlSessionFactory sessionFactory = null;

    // 创建sessionFactory对象，因为整个应用程序只需要一个实例对象，故用静态代码块
    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /*
    * @Description  返回sessionFactory对象 工厂对象
    * @Author 284668461@qq.com
    * @Date 16:15 2020/4/21
    * @Param []
    * @return org.apache.ibatis.session.SqlSession
    **/
    public static SqlSession  getSessionFactory() {
        return sessionFactory.openSession();
    }



}
