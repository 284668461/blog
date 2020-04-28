package com.blog.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("blog")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @PostMapping("getAllBlog")
    @ResponseBody
    public String getAllBlog(String page){





        if(page == null){

            page = "1";
        }

        List m =  blogService.getAllBlog(Integer.parseInt(page));

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";


        String jsonString = JSON.toJSONString(m, SerializerFeature.WriteDateUseDateFormat);

        return jsonString;

    }




    /*
     * @Description 按标签获取博客
     * @Author 284668461@qq.com
     * @Date 16:21 2020/4/22
     * @Param [json]
     * @return java.lang.String
     **/
    @PostMapping("getBlogByTag")
    @ResponseBody
    public String getBlogByTag(@RequestBody JSONObject json){


        String tag = json.getString("tag");

        List m =  blogService.getBlogByTag(tag);

        return JSON.toJSONString(m);
    }




    /*
     * @Description 按分类获取博客
     * @Author 284668461@qq.com
     * @Date 16:21 2020/4/22
     * @Param [json]
     * @return java.lang.String
     **/
    @PostMapping("getBlogByClassify")
    @ResponseBody
    public String getBlogByClassify(int classifyid){

//        int classifyid = Integer.parseInt(json.getString("classifyid"));

        List m =  blogService.getBlogByClassify(classifyid);

        return JSON.toJSONString(m);
    }






    @PostMapping("getBlogByHot")
    @ResponseBody
    public String getBlogByHot(@RequestBody JSONObject json) {

        List m =  blogService.getBlogByHot();

        return JSON.toJSONString(m);
    }




    @PostMapping("getTag")
    @ResponseBody
    public String getTag(){

        List m =  blogService.getTag();

        return JSON.toJSONString(m);
    }



    @PostMapping("getClassify")
    @ResponseBody
    public String getClassify(){

        List ls =  blogService.getClassify();


        return JSONObject.toJSONString(ls, SerializerFeature.WriteMapNullValue);
    }



}
