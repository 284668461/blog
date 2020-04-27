package com.blog.other;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @Description 常用工具类
 * @Author 284668461@qq.com
 * @Date 16:31 2020/4/27
 **/
@Configuration
public class Tool {



    /*
     * @Description 清洗字符串,去除空格换行等
     * @Author 284668461@qq.com
     * @Date 16:34 2020/4/27
     * @Param [str]
     * @return java.lang.String
     **/
    public String wipeOffStr(String str){

        System.out.println("传入的字符串");
        System.out.println(str);
        System.out.println("截取后的字符串");


        String temp = str.replaceAll(" ","")
                .replaceAll("\n","")
                .trim();
        System.out.println(temp);
        return temp;


    }



    public String[] removeArrayNull(String[] arr){


        List<String> strList= Arrays.asList(arr);
        List<String> newArr=new ArrayList<String>();
        for (int i = 0; i <strList.size(); i++) {
            if (strList.get(i)!=null&&!strList.get(i).equals("")){
                newArr.add(  wipeOffStr(strList.get(i) ) );
            }
        }
        String[] strNewArray = newArr.toArray(new String[newArr.size()]);
        return   strNewArray;


    }
}
