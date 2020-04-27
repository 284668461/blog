package com.blog.dao;

public interface OtherMapper{







    /*
     * @Description 更新网站访问
     * @Author 284668461@qq.com
     * @Date 17:12 2020/4/22
     * @Param []
     * @return int
     **/
    int updateVisitor ();



    /*
     * @Description 更新博文访问
     * @Author 284668461@qq.com
     * @Date 17:12 2020/4/22
     * @Param []
     * @return int
     **/
    int updateBlogVisitor ();




    /*
     * @Description 插入评论
     * @Author 284668461@qq.com
     * @Date 17:15 2020/4/22
     * @Param []
     * @return int
     **/
    int  insertComment();


}
