package com.blog.Controller;

import com.alibaba.fastjson.JSONObject;
import com.blog.other.Tool;
import com.blog.other.upFile;
import com.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@RequestMapping("admin")
public class AdminController {


    @Autowired
    private upFile uf;

    @Autowired
    private AdminService ad;

    @Autowired
    private Tool tool;

    /*
     * @Description 接受markdown 插件上传上来的图片
     * @Author 284668461@qq.com
     * @Date 15:22 2020/4/27
     * @Param [file, request]
     * @return com.alibaba.fastjson.JSONObject
     **/
    @RequestMapping("/uploadImg")
    @ResponseBody
    //接收图片的参数名需要为"editormd-image-file"
    public JSONObject uploadImage(@RequestParam("editormd-image-file") MultipartFile file, HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();

        String flag = uf.upImg(file);

        if(flag.equals("false")){

            jsonObject.put("success", 0);//图片上传失败的信息码
            jsonObject.put("message", "upload error!");//信息
        }else{

            jsonObject.put("url", flag);//图片回显地址，即文件存放地址，应为虚拟路径
            jsonObject.put("success", 1);//图片上传成功的信息码
            jsonObject.put("message", "upload success!");//信息
        }

        return jsonObject;
    }



    /*
     * @Description 新增博客
     * @Author 284668461@qq.com
     * @Date 16:51 2020/4/27
     * @Param [req, res, file]
     * @return java.lang.String
     **/
    @PostMapping("insertBlog")
    @ResponseBody
    public Boolean insertBlog(HttpServletRequest req,
                             HttpServletResponse res,
                             MultipartFile file){


        String title = req.getParameter("title");
        String original = req.getParameter("original");
        String classify = req.getParameter("classify");
        String Tag = req.getParameter("Tag");
        String body = req.getParameter("body");
        Boolean blogIsDraft = Boolean.parseBoolean( req.getParameter("blogIsDraft") );
        Boolean blogIsComment = Boolean.parseBoolean( req.getParameter("blogIsComment"));
        Boolean blogIsAdmire = Boolean.parseBoolean( req.getParameter("blogIsAdmire"));

        String author = req.getParameter("author");
        String path = req.getParameter("path");


        String flag ="";

        if(file!=null){
            //保存上传的首图
            flag = uf.upImg(file);
        }


        Map m = new HashMap();

        m.put("title",title);
        m.put("coverPath",flag);
        m.put("classify",classify);
        m.put("auther",1);
        m.put("original",original);
        m.put("body",body);
        m.put("blogIsDraft",blogIsDraft);
        m.put("blogIsComment",blogIsComment);
        m.put("blogIsAdmire",blogIsAdmire);

//        插入博客
        int insertBolgResNum = ad.insertBolg(m);

//        查询出博客id
        int blodId = ad.selectBolgId(m);

//        新增博客分类
        int insertBlogClassifyResNum = ad.insertBlogClassify(blodId,classify);

        //新增博客标签
        //分割标签并清洗后保存为数组
        if(Tag!=null){
            String[] TagArr = tool.removeArrayNull( Tag.split(" ") );

            if(TagArr.length>0){
                ad.insertBlogTag(blodId,TagArr);
            }

        }

        //新增博客版权信息
        ad.insertCopyright(blodId,original,author,path);


        return true;
    }





    /*
     * @Description 查询标签是否已存在
     * @Author 284668461@qq.com
     * @Date 15:58 2020/4/29
     * @Param [Tag]
     * @return java.lang.Boolean
     **/
    @PostMapping("queryTag")
    @ResponseBody
    public Boolean queryTag(String Tag){

        if(ad.queryTag(tool.wipeOffStr(Tag))>0){
            return false;
        }else{
            return  true;
        }

    }



    /*
     * @Description 插入标签
     * @Author 284668461@qq.com
     * @Date 16:01 2020/4/29
     * @Param [Tag]
     * @return java.lang.Boolean
     **/
    @PostMapping("insertTag")
    @ResponseBody
    public Boolean insertTag(String Tag){

        if(ad.insertTag(tool.wipeOffStr(Tag))>0){
            return true;
        }else{
            return  false;
        }

    }



    /*
     * @Description 查询分类是否存在
     * @Author 284668461@qq.com
     * @Date 16:00 2020/4/29
     * @Param [classify]
     * @return java.lang.Boolean
     **/
    @PostMapping("queryClassify")
    @ResponseBody
    public Boolean queryClassify(String classify){

        if(ad.queryClassify(tool.wipeOffStr(classify))>0){
            return false;
        }else{
            return  true;
        }

    }



    /*
     * @Description 插入分类
     * @Author 284668461@qq.com
     * @Date 16:01 2020/4/29
     * @Param [classify]
     * @return java.lang.Boolean
     **/
    @PostMapping("insertClassify")
    @ResponseBody
    public Boolean insertClassify(String classify){

        if(ad.insertClassify(tool.wipeOffStr(classify))>0){
            return true;
        }else{
            return  false;
        }

    }




}
