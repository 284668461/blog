package com.blog.service;


import java.util.List;
import java.util.List;

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
    List  getBlogByTag(String tag);


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




}
