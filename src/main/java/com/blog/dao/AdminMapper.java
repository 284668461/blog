package com.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminMapper {




    /*
     * @Description 新增博客
     * @Author 284668461@qq.com
     * @Date 16:05 2020/4/27
     * @Param [map]
     * @return int
     **/
    @Insert("insert into blog(title,cover_img_path,blog_body,author,publish_date,update_date,admire_flag,Comment_flag) " +
            "values(#{title},#{coverPath},#{body},#{auther},now(),now(),#{blogIsAdmire},#{blogIsComment})")
    int insertBlog(Map map);

    /*
     * @Description 新增博客分类
     * @Author 284668461@qq.com
     * @Date 16:05 2020/4/27
     * @Param [blogId, classify]
     * @return int
     **/
    @Insert("insert into blog_classify(blog_id,type_id,time) " +
            " values(${blogId},(select id from b_classify where name=#{classify}), now() )")
    int insertBlogClassify(int blogId,String classify);


    /*
     * @Description 新增博客标签
     * @Author 284668461@qq.com
     * @Date 16:06 2020/4/27
     * @Param [blogId, tab]
     * @return int
     **/
    @Insert("<script>" +
            "insert into blog_tag(blog_id,tag_id,time)\n" +
            "values" +
            "<foreach collection='arr'  item='i' separator=','  >" +
            "(#{blogId}, (select id from b_tag where name= #{i}), now())" +
            "</foreach>"+
            "</script>")
    int insertBlogTab(int blogId,String[] arr);


    /*
     * @Description 查询出博客id
     * @Author 284668461@qq.com
     * @Date 16:14 2020/4/27
     * @Param [hm]
     * @return int
     **/
    @Select("select id from blog where" +
            "title = #{title}")
    int selectBolgId(Map m);

}
