package com.blog.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blog.other.Tool;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("blog")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private Tool tool;


    /*
     * @Description 获得所有博客
     * @Author 284668461@qq.com
     * @Date 10:12 2020/5/4
     * @Param [page]
     * @return java.lang.String
     **/
    @PostMapping("getAllBlog")
    @ResponseBody
    public String getAllBlog(String page){

        if(page == null){
            page = "1";
        }

        List m =  blogService.getAllBlog(Integer.parseInt(page));

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";

        return JSON.toJSONString(m, SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);

    }



    /*
     * @Description 获得博客详情
     * @Author 284668461@qq.com
     * @Date 16:07 2020/5/7
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("getBlogDetail")
    @ResponseBody
    public String getBlogDetail(int id,HttpServletRequest req){


        Map m = blogService.getBlogDetail(id,req);

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";

        return JSON.toJSONString(m, SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);

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
    public String getBlogByTag(int tagId){

        List m =  blogService.getBlogByTag(tagId);

        return JSON.toJSONString(m,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
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

        List m =  blogService.getBlogByClassify(classifyid);

        return JSON.toJSONString(m,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
    }





    /*
     * @Description 获得热文
     * @Author 284668461@qq.com
     * @Date 10:13 2020/5/4
     * @Param [json]
     * @return java.lang.String
     **/
    @PostMapping("getBlogByHot")
    @ResponseBody
    public String getBlogByHot() {

        List m =  blogService.getBlogByHot();

        return JSON.toJSONString(m,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
    }



    /*
     * @Description 获得所有标签
     * @Author 284668461@qq.com
     * @Date 10:13 2020/5/4
     * @Param []
     * @return java.lang.String
     **/
    @PostMapping("getTag")
    @ResponseBody
    public String getTag(){

        List m =  blogService.getTag();

        return JSON.toJSONString(m);
    }



    /*
     * @Description 获得分类
     * @Author 284668461@qq.com
     * @Date 10:14 2020/5/4
     * @Param []
     * @return java.lang.String
     **/
    @PostMapping("getClassify")
    @ResponseBody
    public String getClassify(){

        List ls =  blogService.getClassify();

        return JSONObject.toJSONString(ls, SerializerFeature.WriteMapNullValue);
    }






    /*
     * @Description 删除博客
     * @Author 284668461@qq.com
     * @Date 10:14 2020/5/4
     * @Param [id]
     * @return java.lang.Boolean
     **/
    @PostMapping("delBlog")
    @ResponseBody
    public Boolean delBlog(int id){

        if( blogService.delBlog(id)>0){
            return  true;
        }else{
            return  false;
        }

    }


    /*
     * @Description 查询博客
     * @Author 284668461@qq.com
     * @Date 10:17 2020/5/4
     * @Param [title]
     * @return java.lang.String
     **/
    @PostMapping("getBlogByQuery")
    @ResponseBody
    public String getBlogByQuery(String title){


        List ls =  blogService.getBlogByQuery(title);

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";

        return JSON.toJSONString(ls, SerializerFeature.WriteDateUseDateFormat);
    }



    /*
     * @Description 混合查询博文
     * @Author 284668461@qq.com
     * @Date 10:23 2020/5/4
     * @Param [tagId, classifyId, title]
     * @return java.lang.String
     **/
    @PostMapping("getBlogByMixtureQuery")
    @ResponseBody
    public String getBlogByMixtureQuery(int tagId,int classifyId,String title){




        List ls =  blogService.getBlogByMixtureQuery(tagId,classifyId,tool.wipeOffStr(title));

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";

        return JSON.toJSONString(ls, SerializerFeature.WriteDateUseDateFormat);
    }



    /*
     * @Description 获得时间线
     * @Author 284668461@qq.com
     * @Date 11:00 2020/5/13
     * @Param []
     * @return java.lang.String
     **/
    @PostMapping("getTimeLine")
    @ResponseBody
    public String getTimeLine() {

        List m =  blogService.getTimeLine();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        return JSON.toJSONString(m,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
    }






    /*
     * @Description 获得博客评论
     * @Author 284668461@qq.com
     * @Date 15:45 2020/5/14
     * @Param [blogId]
     * @return java.lang.String
     **/
    @PostMapping("getBlogComment")
    @ResponseBody
    public String getBlogComment(int blogId){
        List m =  blogService.getBlogComment(blogId);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        return JSON.toJSONString(m,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
    }




    /*
     * @Description 添加博客评论
     * @Author 284668461@qq.com
     * @Date 11:00 2020/5/13
     * @Param [blogId, nickName, commentBody, replyCommentId, ip]
     * @return java.lang.Boolean
     **/
    @PostMapping("insertBlogComment")
    @ResponseBody
    public Boolean insertBlogComment(String nickName, String commentBody, int blogId,int replyCommentId, HttpServletRequest req){


       if(blogService.insertBlogComment(nickName,commentBody,blogId,replyCommentId,tool.getIRealIPAddr(req))){
           return true;
       }else{
           return false;
       }

    }

}
