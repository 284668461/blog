package com.blog.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Configuration;

import java.util.*;

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

        String temp = str.replaceAll(" ","")
                .replaceAll("\n","")
                .trim();

        return temp;

    }



    /*
     * @Description 去除数组中的空元素
     * @Author 284668461@qq.com
     * @Date 22:37 2020/4/27
     * @Param [arr]
     * @return java.lang.String[]
     **/
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



    /*
     * @Description 拼凑版权信息
     * @Author 284668461@qq.com
     * @Date 17:37 2020/5/7
     * @Param [copyrightInfo]
     * @return java.util.Map
     **/
    public Map generateCopyright(List copyrightInfo){

        Map m = new HashMap();

        System.out.println("copyrightInfotoList");




        Map tempMap = (Map)copyrightInfo.get(0);


        int copyrightFlag = Integer.parseInt((String)tempMap.get("copyright_flag"));



        if(copyrightInfo!=null){

            if(copyrightFlag == 0){
                m.put("copyrightFlag","原创");
                m.put("copyrightInfo","本文为博主的原创文章，转载请附上原文出处链接及本声明。");
            }else if(copyrightFlag == 1){
                m.put("copyrightFlag","转载");
                m.put("copyrightInfo","本文为博主的转载文章，转载请附上原文出处链接。");
                m.put("copyrightAuthor",tempMap.get("authorauthor"));
            }else{
                m.put("copyrightFlag","翻译");
                m.put("copyrightInfo","本文为博主的翻译文章，转载请附上原文出处链接及本声明。");
            }

            m.put("path",tempMap.get("path"));
        }else{

            m.put("copyrightFlag","原创");
            m.put("copyrightInfo","本文为博主的原创文章，转载请附上原文出处链接及本声明。");
        }


        return m;


    }



}
