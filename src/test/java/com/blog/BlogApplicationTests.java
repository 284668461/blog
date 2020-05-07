package com.blog;

import com.blog.other.Tool;
import com.blog.service.AdminService;
import com.blog.service.BlogService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class BlogApplicationTests {



    @Autowired
    private BlogService bs;

    @Test
    void contextLoads() {

        System.out.println("1---------------------");

        System.out.println(
                bs.getBlogByMixtureQuery(0,0,"1")
        );


        System.out.println("2---------------------");

        System.out.println(
                bs.getBlogByMixtureQuery(3,0,"1")
        );

        System.out.println("3---------------------");
        System.out.println(
                bs.getBlogByMixtureQuery(3,1,"1")
        );
//        SqlSession session = MyBatisUtil.getSessionFactory();

    }

}
