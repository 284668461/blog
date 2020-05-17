package com.blog.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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
     * @Param [blogId, Tag]
     * @return int
     **/
    int insertBlogTag(int blogId,int[] arr);



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




    /*
     * @Description 查询标签是否已存在
     * @Author 284668461@qq.com
     * @Date 10:40 2020/4/29
     * @Param [Tag]
     * @return int
     **/
    int queryTag(String Tag);

    /*
     * @Description 新增标签
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [Tag]
     * @return int
     **/
    int insertTag(String Tag);


    /*
     * @Description 新增分类
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [classify]
     * @return int
     **/
    int insertClassify(String classify);

    /*
     * @Description 查询分类是否存在
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [classify]
     * @return int
     **/
    int queryClassify(String classify);




    /*
     * @Description 插入博客版权信息
     * @Author 284668461@qq.com
     * @Date 21:08 2020/5/8
     * @Param [blogId, copyrightFlag, author, path]
     * @return int
     **/
    int insertCopyright(int blogId,String copyrightFlag,String author,String path);


}
