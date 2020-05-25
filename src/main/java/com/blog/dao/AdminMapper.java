package com.blog.dao;

import org.apache.ibatis.annotations.Delete;
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
    @Insert("insert into blog(title,cover_img_path,blog_intro,blog_body,author,publish_date,update_date,admire_flag,Comment_flag,draft_flag) " +
            "values(#{title},#{coverPath},#{blogIntro},#{body},#{auther},now(),now(),#{blogIsAdmire},#{blogIsComment},#{blogIsDraft})")
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
    int insertBlogClassify(int blogId, String classify);


    /*
     * @Description 新增博客标签
     * @Author 284668461@qq.com
     * @Date 16:06 2020/4/27
     * @Param [blogId, Tag]
     * @return int
     **/
    @Insert("<script>" +
            "insert into blog_tag(blog_id,tag_id,time)\n" +
            "values" +
            "<foreach collection='arr'  item='i' separator=','  >" +
            "(#{blogId}, #{i}, now())" +
            "</foreach>" +
            "</script>")
    int insertBlogTag(int blogId, int[] arr);


    /*
     * @Description 查询出博客id
     * @Author 284668461@qq.com
     * @Date 16:14 2020/4/27
     * @Param [hm]
     * @return int
     **/
    @Select("select id from blog where title = #{title}")
    int selectBolgId(Map m);


    /*
     * @Description 删除博客
     * @Author 284668461@qq.com
     * @Date 17:42 2020/4/28
     * @Param [blogId]
     * @return int
     **/
    @Delete("update blog set del_flag = true where id = #{blogId}")
    int delBlog(int blogId);


    /*
     * @Description 查询标签是否已存在
     * @Author 284668461@qq.com
     * @Date 10:40 2020/4/29
     * @Param [Tag]
     * @return int
     **/
    @Select("select count(*) from b_tag where name = #{tag}")
    int queryTag(String tag);

    /*
     * @Description 新增标签
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [Tag]
     * @return int
     **/
    @Insert("insert into b_tag (name,time) values(#{tag},now()) ")
    int insertTag(String tag);


    /*
     * @Description 新增分类
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [classify]
     * @return int
     **/
    @Insert("insert into b_classify (name,time) values(#{classify},now()) ")
    int insertClassify(String classify);

    /*
     * @Description 查询分类是否存在
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [classify]
     * @return int
     **/
    @Select("select count(*) from b_classify where name = #{classify} ")
    int queryClassify(String classify);


    /*
     * @Description 插入博客版权信息
     * @Author 284668461@qq.com
     * @Date 21:07 2020/5/8
     * @Param [blogId, copyrightFlag, author, path]
     * @return int
     **/
    @Insert("insert into blog_copyright " +
            "(blog_id,copyright_flag,author,path,time) " +
            "values(#{blogId},#{copyrightFlag},#{author},#{path},now()) ")
    int insertCopyright(int blogId, int copyrightFlag, String author, String path);
}
