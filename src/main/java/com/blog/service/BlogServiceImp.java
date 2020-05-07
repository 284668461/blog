package com.blog.service;

import com.blog.MyBatisUtil;
import com.blog.dao.BlogMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
@Configuration
public class BlogServiceImp implements BlogService {


    /*
     * @Description 获得指定页数博文
     * @Author 284668461@qq.com
     * @Date 16:07 2020/4/22
     * @Param [page]
     * @return java.util.Map
     **/
    @Override
    public List getAllBlog(int page) {


        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        List ls=  bm.getAllBlog(page);

        return ls;

    }




    /*
     * @Description  获得对应标签的博文
     * @Author 284668461@qq.com
     * @Date 17:04 2020/4/22
     * @Param [tag]
     * @return java.util.Map
     **/
    @Override
    public List getBlogByTag(int tagId) {


        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        List ls=  bm.getBlogByTag(tagId);

        return ls;


    }

    /*
     * @Description 获得对应分类的博文
     * @Author 284668461@qq.com
     * @Date 17:04 2020/4/22
     * @Param [Classify]
     * @return java.util.Map
     **/
    @Override
    public List getBlogByClassify(int classifyid) {


        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        List ls=  bm.getBlogByClassify(classifyid);
        return ls;

    }

    /*
     * @Description 获得热文
     * @Author 284668461@qq.com
     * @Date 17:04 2020/4/22
     * @Param []
     * @return java.util.Map
     **/
    @Override
    public List getBlogByHot() {
        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        List ls=  bm.getBlogByHot();

        return ls;
    }

    /*
     * @Description 获得标签
     * @Author 284668461@qq.com
     * @Date 17:04 2020/4/22
     * @Param []
     * @return java.util.Map
     **/
    @Override
    public List getTag() {
        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        List ls=  bm.getTag();

        return ls;
    }


    /*
     * @Description 获得分类
     * @Author 284668461@qq.com
     * @Date 17:04 2020/4/22
     * @Param []
     * @return java.util.Map
     **/
    @Override
    public List getClassify() {

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        List ls=  bm.getClassify();

        return ls;
    }

    /*
     * @Description 删除博客
     * @Author 284668461@qq.com
     * @Date 10:26 2020/5/4
     * @Param [id]
     * @return int
     **/
    @Override
    public int delBlog(int id) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.delBlog(id);
    }


    /*
     * @Description 查询博客
     * @Author 284668461@qq.com
     * @Date 10:26 2020/5/4
     * @Param [title]
     * @return java.util.List
     **/
    @Override
    public List getBlogByQuery(String title) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.getBlogByQuery(title);
    }

    /*
     * @Description 混合查询博客
     * @Author 284668461@qq.com
     * @Date 10:26 2020/5/4
     * @Param [tagId, classifyId, title]
     * @return java.util.List
     **/
    @Override
    public List getBlogByMixtureQuery(int tagId, int classifyId, String title) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.getBlogByMixtureQuery(tagId,classifyId,title);
    }
}
