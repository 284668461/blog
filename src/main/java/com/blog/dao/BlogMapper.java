package com.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
 @Select("select * from b_user where user = #{user} and pass = #{pass}")
 List<Map> getAllBlog(int page);


 /*
  * @Description 获得指定标签的博文
  * @Author 284668461@qq.com
  * @Date 16:13 2020/4/22
  * @Param [tag]
  * @return java.util.Map
  **/
 @Select("")
 List<Map>  getBlogByTag(String tag);





 /*
  * @Description 获得指定分类的博文
  * @Author 284668461@qq.com
  * @Date 16:14 2020/4/22
  * @Param [tag]
  * @return java.util.Map
  **/
 @Select("select * from blog as b\n" +
         "inner join ( select id,blog_id,del_flag from blog_classify) as bc on bc.blog_id = b.id\n" +
         "where  bc.id = #{classifyId} " +
         "and b.del_flag <> '1' or b.del_flag is null\n" +
         "and bc.del_flag <> '1' or bc.del_flag is null")
 List<Map>  getBlogByClassify(int classifyId);







/*
 * @Description 获得热文
 * @Author 284668461@qq.com
 * @Date 16:52 2020/4/22
 * @Param []
 * @return java.util.Map
 **/
 @Select("")
 List<Map>  getBlogByHot();




 /*
  * @Description 获得标签
  * @Author 284668461@qq.com
  * @Date 16:57 2020/4/22
  * @Param []
  * @return java.util.Map
  **/
 @Select("select * from b_tag where del_flag ='false' or del_flag is null ")
 List<Map> getTag();



 /*
  * @Description 获得分类
  * @Author 284668461@qq.com
  * @Date 16:58 2020/4/22
  * @Param
  * @return
  **/

 @Select("select *  from b_classify as c\n" +
         "left join( select *,count(type_id) as num from blog_classify ) as bc on bc.type_id = c.id\n" +
         "left join( select id,del_flag from blog ) as b on b.id = bc.blog_id\n" +
         "where c.del_flag <> '1' or c.del_flag is null\n" +
         "and b.del_flag <> '1' or b.del_flag is null\n" +
         "and bc.del_flag <> '1' or bc.del_flag is null")
 List<Map>  getClassify();


}
