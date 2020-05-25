package com.blog;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/*
 * @Description mybatis  工具类
 * @Author 284668461@qq.com
 * @Date 16:15 2020/4/21
 **/
public class MyBatisUtil {


    private static SqlSessionFactory sqlSessionFactory;

    //    加载资源
    static {

        try {

            String resource = "mybatis-config.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //    创建执行sql 对象
    //    SqlSession 包含了执行sql命令的所有操作方法
    public static SqlSession getSessionFactory() {
        return sqlSessionFactory.openSession(true);
    }

}
