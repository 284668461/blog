package com.blog.service;

import com.blog.MyBatisUtil;
import com.blog.dao.BlogMapper;
import com.blog.other.Tool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Configuration
public class BlogServiceImp implements BlogService {

    @Autowired
    private Tool tool;




    /*
     * @Description 获得所有博客
     * @Author 284668461@qq.com
     * @Date 10:33 2020/5/16
     * @Param []
     * @return java.util.List
     **/
    @Override
    public List getAllBlog() {


        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        //获得博客信息
        // 将博客 内容格式化为 html
        List ls = bm.getAllBlog();

        List tempLs = new ArrayList();

        for (int i = 0; i < ls.size(); i++) {

            Map tempM = (Map)ls.get(i);
            tempM.put("blog_body",tool.markDownStrTohtml((String)tempM.get("blog_body")));
            tempLs.add( tempM );
        }



        return tempLs;




    }

    /*
     * @Description 获得指定页数博文
     * @Author 284668461@qq.com
     * @Date 16:07 2020/4/22
     * @Param [page]
     * @return java.util.Map
     **/
    @Override
    public Map getBlogByPage(int page) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);


        if(page !=0){
            page*=10;
        }


        //获得一页博客信息
        // 将博客 内容格式化为 html
        List ls = bm.getBlogByPage(page);

        List tempLs = new ArrayList();

        for (int i = 0; i < ls.size(); i++) {

            Map tempM = (Map)ls.get(i);
            tempM.put("blog_body",tool.markDownStrTohtml((String)tempM.get("blog_body")));
            tempLs.add(tempM);
        }

        //获得博客总数量
       int blogNum = bm.getBlogNum();

        //计算博客页数
        int blogPage = (int)Math.ceil((float)blogNum/10);


        Map pageM = new HashMap();
        pageM.put("blogTotal",blogNum);
        pageM.put("blogPage",blogPage);


        Map resM = new HashMap();

        resM.put("blogList",tempLs);
        resM.put("pageInfo",pageM);


        return resM;




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



        // 将博客 内容格式化为 html
        List ls = bm.getBlogByTag(tagId);

        List tempLs = new ArrayList();

        for (int i = 0; i < ls.size(); i++) {

            Map tempM = (Map)ls.get(i);
            tempM.put("blog_body",tool.markDownStrTohtml((String)tempM.get("blog_body")));
            tempLs.add(tempM);
        }


        return tempLs;



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



        // 将博客 内容格式化为 html
        List ls = bm.getBlogByClassify(classifyid);

        List tempLs = new ArrayList();

        for (int i = 0; i < ls.size(); i++) {

            Map tempM = (Map)ls.get(i);
            tempM.put("blog_body",tool.markDownStrTohtml((String)tempM.get("blog_body")));
            tempLs.add(tempM);
        }


        return tempLs;

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
    public Map getBlogDetail(int blogId,HttpServletRequest req) {

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



        //保存博客访问记录
        bm.insertBlogVisitor(blogId,tool.getIRealIPAddr(req));


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


    /*
     * @Description 插入博客评论
     * @Author 284668461@qq.com
     * @Date 16:08 2020/5/14
     * @Param [blogId, nickName, commentBody, replyCommentId, ip]
     * @return boolean
     **/
    @Override
    public boolean insertBlogComment(String nickName, String commentBody, int blogId,int replyCommentId, String ip){

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        Map m = new HashMap();

        m.put("blogId",blogId);
        m.put("nickName",nickName);
        m.put("commentBody",commentBody);
        m.put("replyCommentId",replyCommentId);
        m.put("ip",ip);


//       若该用户是匿名
        if((nickName == null)|| (nickName == "")){
            m.put("nickName","匿名");
            m.put("iconPath","img/icon/anonymity.png");
        }else{

//       判断该用户是否已在本条博客评论过

            if(bm.queryCommentByBlog(blogId,ip).size()>0){
                //博客评论过，使用以前生成的头像
                m.put("iconPath",bm.getCommentBlogIcon(blogId,ip));

            }else{
                //没评论过,则生成头像
                m.put("iconPath",tool.getIcon());
            }

        }


        if(bm.insertBlogComment(m)>0){
            return true;
        }

        return false;
    }




    /*
     * @Description 获得博客总数
     * @Author 284668461@qq.com
     * @Date 16:46 2020/5/15
     * @Param []
     * @return int
     **/
    public int getBlogNum(){

        SqlSession session = MyBatisUtil.getSessionFactory();

        BlogMapper bm = session.getMapper(BlogMapper.class);

        return  bm.getBlogNum();
    };
}
