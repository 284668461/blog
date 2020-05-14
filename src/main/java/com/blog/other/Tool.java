package com.blog.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
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


        if(copyrightInfo!=null){

            Map tempMap = null;
            try {
                tempMap = (Map)copyrightInfo.get(0);


                int copyrightFlag = Integer.parseInt((String)tempMap.get("copyright_flag"));


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



            } catch (Exception e) {
                m.put("copyrightFlag","原创");
                m.put("copyrightInfo","本文为博主的原创文章，转载请附上原文出处链接及本声明。");
            }


        }else{

            m.put("copyrightFlag","原创");
            m.put("copyrightInfo","本文为博主的原创文章，转载请附上原文出处链接及本声明。");
        }


        return m;


    }


    /*
     * @Description Markdown字符串 转 html
     * @Author 284668461@qq.com
     * @Date 15:08 2020/5/8
     * @Param [str]
     * @return java.lang.String
     **/
    public String markDownStrTohtml(String str){

        //  标题# 后面需要有空格才会正确渲染标题
        Parser parser = Parser.builder().build();
        Node document = parser.parse(str);
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        return renderer.render(document);

    }






    /*
     * @Description 获得IP地址
     * @Author 284668461@qq.com
     * @Date 10:21 2020/5/14
     * @Param [request]
     * @return java.lang.String
     **/
    public String getIRealIPAddr(HttpServletRequest request) {
        return request.getRemoteAddr();
    }




    /*
     * @Description 随机获得一张头像
     * @Author 284668461@qq.com
     * @Date 17:33 2020/5/14
     * @Param []
     * @return java.lang.String
     **/
    public String getIcon(){

        return "img/icon/icon_"
                + new Random().nextInt(20)
                +".jpg";
    }

}
