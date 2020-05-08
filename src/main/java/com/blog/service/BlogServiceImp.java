package com.blog.service;

import com.blog.MyBatisUtil;
import com.blog.dao.BlogMapper;
import com.blog.other.Tool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Configuration
public class BlogServiceImp implements BlogService {

    @Autowired
    private Tool tool;

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

        return bm.getAllBlog(page);

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

        return bm.getBlogByTag(tagId);


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

        return bm.getBlogByClassify(classifyid);

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

        return bm.getBlogByHot();
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

        return  bm.getTag();
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

        return bm.getClassify();
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

    /*
     * @Description 获得时间轴
     * @Author 284668461@qq.com
     * @Date 15:23 2020/5/7
     * @Param []
     * @return java.util.List
     **/
    @Override
    public List getTimeLine() {
        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.getTimeLine();
    }



    /*
     * @Description 获得博客详情
     * @Author 284668461@qq.com
     * @Date 16:13 2020/5/7
     * @Param [blogId]
     * @return java.util.List
     **/
    @Override
    public Map getBlogDetail(int blogId) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        Map m = new HashMap();


        //获得博文详情,并将博文内容转换为 html
        Map blogDetail = (Map)bm.getBlogDetail(blogId).get(0);
        blogDetail.put("blog_body",tool.markDownStrTohtml( (String)blogDetail.get("blog_body") ));

        m.put("blogDetail",blogDetail);
        //获得博文标签
        m.put("blogTag",bm.getBlogTag(blogId));
        //获得博文评论
        m.put("comment",bm.getBlogComment(blogId));
        // 获得并生成版权信息
        m.put("copyright",tool.generateCopyright(bm.getBlogCopyright(blogId)));


        return m;
    }

    /*
     * @Description 获得博客标签
     * @Author 284668461@qq.com
     * @Date 16:13 2020/5/7
     * @Param [blogId]
     * @return java.util.List
     **/
    @Override
    public List getBlogTag(int blogId) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.getBlogTag(blogId);
    }

    /*
     * @Description 获得博客评论
     * @Author 284668461@qq.com
     * @Date 16:13 2020/5/7
     * @Param [blogId]
     * @return java.util.List
     **/
    @Override
    public List getBlogComment(int blogId) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.getBlogComment(blogId);
    }

    /*
     * @Description 获得博客版权
     * @Author 284668461@qq.com
     * @Date 16:13 2020/5/7
     * @Param [blogId]
     * @return java.util.List
     **/
    @Override
    public List getBlogCopyright(int blogId) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.getBlogCopyright(blogId);
    }
}
