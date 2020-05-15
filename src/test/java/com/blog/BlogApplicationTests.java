package com.blog;

import com.blog.dao.BlogMapper;
import com.blog.other.Tool;
import com.blog.service.AdminService;
import com.blog.service.BlogService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private AdminService ad;

    @Autowired
    private BlogService bs;

    @Autowired
    private Tool tool;

    @Test
    void contextLoads() {

        int a = 12;
        int b = 10;

        System.out.println((float)a/b);
        System.out.println(Math.ceil((float)a/b));
        System.out.println((int)Math.ceil((float)a/b));



    }

}
