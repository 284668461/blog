package com.blog.service;

import java.util.HashMap;
import java.util.Map;

public interface AdminService {




    /*
     * @Description 新增博客
     * @Author 284668461@qq.com
     * @Date 16:08 2020/4/27
     * @Param [hm]
     * @return int
     **/
    int insertBolg(Map m);

    /*
     * @Description 新增博客分类
     * @Author 284668461@qq.com
     * @Date 16:08 2020/4/27
     * @Param [blogId, classify]
     * @return int
     **/
    int insertBlogClassify(int blogId,String classify);


    /*
     * @Description 新增博客标签
     * @Author 284668461@qq.com
     * @Date 16:08 2020/4/27
     * @Param [blogId, tab]
     * @return int
     **/
    int insertBlogTab(int blogId,String[] arr);



    /*
     * @Description 查询出博客id
     * @Author 284668461@qq.com
     * @Date 16:16 2020/4/27
     * @Param [hm]
     * @return int
     **/
    int selectBolgId(Map m);


    /*
     * @Description 删除博客
     * @Author 284668461@qq.com
     * @Date 17:45 2020/4/28
     * @Param [blogId]
     * @return int
     **/
    int delBlog(int blogId);

}
