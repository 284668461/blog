package com.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;


@Mapper
public interface BlogMapper {



 /*
  * @Description 获得指定页数博文
  * @Author 284668461@qq.com
  * @Date 16:04 2020/4/22
  * @Param [page]
  * @return java.util.Map
  **/
// @Select("select *,b.id as blog_id  from blog as b\n" +
//         "left join( select blog_id,count(blog_id) as visitor_num from blog_visitor group by blog_id) as bv on bv.blog_id = b.id\n" +
//         "left join( select blog_id,count(blog_id) as comment_num from blog_comment group by blog_id) as bc on bc.blog_id = b.id\n" +
//         "left join(select id,name,user_icon from b_user ) as bu on bu.id = b.author\n" +
//         "where b.del_flag <> '1' or b.del_flag is null\n" +
//         "order by b.publish_date")
 @Select("select *,b.id as blogid  from blog as b \n" +
         "left join( select blog_id,count(blog_id) as visitor_num from blog_visitor group by blog_id) as bv on bv.blog_id = b.id\n" +
         "left join( select blog_id,count(blog_id) as comment_num from blog_comment group by blog_id) as bc on bc.blog_id = b.id\n" +
         "left join( select id,name,user_icon from b_user ) as bu on bu.id = b.author\n" +
         "left join( select * from blog_classify  ) as  bclassify  on bclassify.blog_id = b.id\n" +
         "left join( select id,name as classify_name from b_classify  ) as  c  on c.id = bclassify.type_id \n" +
         "where b.del_flag <> '1' or b.del_flag is null \n" +
         "order by b.publish_date desc")
 List<Map> getAllBlog(int page);


 /*
  * @Description 获得指定标签的博文
  * @Author 284668461@qq.com
  * @Date 16:13 2020/4/22
  * @Param [tag]
  * @return java.util.Map
  **/
 @Select("select *,b.id as blogid from blog as b \n" +
         "left join( select blog_id,count(blog_id) as visitor_num from blog_visitor group by blog_id) as bv on bv.blog_id = b.id \n" +
         "left join( select blog_id,count(blog_id) as comment_num from blog_comment group by blog_id) as bc on bc.blog_id = b.id \n" +
         "left join( select id,name,user_icon from b_user ) as bu on bu.id = b.author \n" +
         "left join( select * from blog_classify ) as bclassify on bclassify.blog_id = b.id \n" +
         "left join( select id,name as classify_name from b_classify ) as c on c.id = bclassify.type_id \n" +
         "inner join( select id,blog_id,tag_id from blog_tag  where tag_id = #{tagId}) as bt on bt.blog_id = b.id \n" +
         "where ( b.del_flag <> '1' or b.del_flag is null  )\n" +
         "order by b.publish_date desc ")
 List<Map>  getBlogByTag(int tagId);





 /*
  * @Description 获得指定分类的博文
  * @Author 284668461@qq.com
  * @Date 16:14 2020/4/22
  * @Param [tag]
  * @return java.util.Map
  **/
 @Select("select *,b.id as blogid from blog as b \n" +
         "left join( select blog_id,count(blog_id) as visitor_num from blog_visitor group by blog_id) as bv on bv.blog_id = b.id \n" +
         "left join( select blog_id,count(blog_id) as comment_num from blog_comment group by blog_id) as bc on bc.blog_id = b.id \n" +
         "left join( select id,name,user_icon from b_user ) as bu on bu.id = b.author \n" +
         "left join( select * from blog_classify ) as bclassify on bclassify.blog_id = b.id \n" +
         "inner join( select id,name as classify_name from b_classify where id=#{classifyId} ) as c on c.id = bclassify.type_id \n" +
         "where ( b.del_flag <> '1' or b.del_flag is null  )\n" +
         "order by b.publish_date desc ")
 List<Map>  getBlogByClassify(int classifyId);





 /*
  * @Description 模糊查询博客
  * @Author 284668461@qq.com
  * @Date 17:52 2020/4/28
  * @Param [title]
  * @return java.util.List<java.util.Map>
  **/
 @Select("select *,b.id as blogid from blog as b \n" +
         "left join( select blog_id,count(blog_id) as visitor_num from blog_visitor group by blog_id) as bv on bv.blog_id = b.id \n" +
         "left join( select blog_id,count(blog_id) as comment_num from blog_comment group by blog_id) as bc on bc.blog_id = b.id \n" +
         "left join( select id,name,user_icon from b_user ) as bu on bu.id = b.author \n" +
         "left join( select * from blog_classify ) as bclassify on bclassify.blog_id = b.id \n" +
         "left join( select id,name as classify_name from b_classify ) as c on c.id = bclassify.type_id \n" +
         "where ( b.del_flag <> '1' or b.del_flag is null  )\n" +
         "and b.title like '%#{title}%'\n" +
         "order by b.publish_date desc ")
 List<Map>  getBlogByQuery(String title);



/*
 * @Description 混合查询
 * @Author 284668461@qq.com
 * @Date 10:08 2020/5/4
 * @Param [tagId, classifyId, title]
 * @return java.util.List<java.util.Map>
 **/
 @Select("<script> " +
         "select *,b.id as blogid from blog as b \n" +
         "left join( select blog_id,count(blog_id) as visitor_num from blog_visitor group by blog_id) as bv on bv.blog_id = b.id \n" +
         "left join( select blog_id,count(blog_id) as comment_num from blog_comment group by blog_id) as bc on bc.blog_id = b.id \n" +
         "left join( select id,name,user_icon from b_user ) as bu on bu.id = b.author \n" +
         "inner join( select * from blog_classify ) as bclassify on bclassify.blog_id = b.id \n" +
         "left join( select id,name as classify_name from b_classify ) as c on c.id = bclassify.type_id \n" +
         "inner join( select * from blog_tag ) as bt on bt.blog_id = b.id \n" +
         "where ( b.del_flag != '1' or b.del_flag is null  ) \n" +
         "<if test='title!=null'> and b.title like concat('%',#{title},'%') </if> \n" +
         "<if test='classifyId &gt; 0'> and bclassify.type_id= #{classifyId} </if>\n" +
         "<if test='tagId &gt; 0'> and bt.tag_id= #{tagId} </if>\n" +
         "order by b.publish_date desc " +
         " </script>")
// @Select("")
 List<Map> getBlogByMixtureQuery(int tagId,int classifyId,String title);




/*
 * @Description 获得热文
 * @Author 284668461@qq.com
 * @Date 16:52 2020/4/22
 * @Param []
 * @return java.util.Map
 **/
 @Select("select *,b.id as blogid from blog as b \n" +
         "left join( select blog_id,count(blog_id) as visitor_num from blog_visitor group by blog_id) as bv on bv.blog_id = b.id \n" +
         "left join( select blog_id,count(blog_id) as comment_num from blog_comment group by blog_id) as bc on bc.blog_id = b.id \n" +
         "left join( select id,name,user_icon from b_user ) as bu on bu.id = b.author \n" +
         "left join( select * from blog_classify ) as bclassify on bclassify.blog_id = b.id \n" +
         "left join( select id,name as classify_name from b_classify ) as c on c.id = bclassify.type_id \n" +
         "where ( b.del_flag <> '1' or b.del_flag is null  )\n" +
         "order by bv.visitor_num desc \n" +
         "limit 0,5")
 List<Map>  getBlogByHot();




 /*
  * @Description 获得标签
  * @Author 284668461@qq.com
  * @Date 16:57 2020/4/22
  * @Param []
  * @return java.util.Map
  **/
 @Select("select * from b_tag where del_flag <> '1' or del_flag is null ")
 List<Map> getTag();



 /*
  * @Description 获得分类
  * @Author 284668461@qq.com
  * @Date 16:58 2020/4/22
  * @Param
  * @return
  **/


 @Select("select * from b_classify where del_flag <> '1' or del_flag is null ")
 List<Map>  getClassify();




/*
 * @Description 获得时间轴事件
 * @Author 284668461@qq.com
 * @Date 18:01 2020/4/28
 * @Param []
 * @return java.util.List<java.util.Map>
 **/
 @Select("select * from timeline order by date desc")
 List<Map>  getTimeLine();




 /*
  * @Description 删除博客，将删除标记改为true
  * @Author 284668461@qq.com
  * @Date 16:31 2020/5/3
  * @Param [id]
  * @return int
  **/
 @Update("update blog set del_flag = 1 where id = #{id}")
 int delBlog(int id);



}
