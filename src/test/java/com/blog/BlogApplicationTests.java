package com.blog;

import com.blog.other.Tool;
import com.blog.service.AdminService;
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
    private AdminService ad;
    @Autowired
    private Tool tool;

    @Test
    void contextLoads() {

        String a = "w   a   cv    fsaaf   ger   asfwef";
//       tool.wipeOffStr(a);

        String[] tabArr = tool.removeArrayNull( a.split(" ") );


        for(String it :tabArr){
            System.out.println("----"+it+"----");

        }




    }

}
