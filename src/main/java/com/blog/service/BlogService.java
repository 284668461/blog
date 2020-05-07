package com.blog.service;


import java.util.List;
import java.util.List;
import java.util.Map;

public interface BlogService {

    /*
     * @Description   获得指定页数博文
     * @Author 284668461@qq.com
     * @Date 16:01 2020/4/22
     * @Param []
     * @return java.util.List
     **/
    List getAllBlog(int page);


    /*
     * @Description 获得指定标签的博文
     * @Author 284668461@qq.com
     * @Date 16:13 2020/4/22
     * @Param [tag]
     * @return java.util.List
     **/
    List  getBlogByTag(int tagId);


    /*
     * @Description 获得指定分类的博文
     * @Author 284668461@qq.com
     * @Date 16:14 2020/4/22
     * @Param [tag]
     * @return java.util.List
     **/
    List  getBlogByClassify(int classifyid);





    /*
     * @Description 获得热文
     * @Author 284668461@qq.com
     * @Date 16:53 2020/4/22
     * @Param []
     * @return java.util.List
     **/
    List  getBlogByHot();




    /*
     * @Description 获得所有标签
     * @Author 284668461@qq.com
     * @Date 17:00 2020/4/22
     * @Param []
     * @return java.util.List
     **/
    List getTag();


    /*
     * @Description  获得所有标签
     * @Author 284668461@qq.com
     * @Date 17:00 2020/4/22
     * @Param []
     * @return java.util.List
     **/
    List  getClassify();


    /*
     * @Description 删除博客
     * @Author 284668461@qq.com
     * @Date 16:34 2020/5/3
     * @Param [id]
     * @return int
     **/
    int delBlog(int id);



    /*
     * @Description 查询博客
     * @Author 284668461@qq.com
     * @Date 10:18 2020/5/4
     * @Param [title]
     * @return java.util.List
     **/
    List getBlogByQuery(String title);



    /*
     * @Description 混合查询博文
     * @Author 284668461@qq.com
     * @Date 10:22 2020/5/4
     * @Param [tagId, classifyId, title]
     * @return java.util.List
     **/
    List getBlogByMixtureQuery(int tagId,int classifyId,String title);





}
