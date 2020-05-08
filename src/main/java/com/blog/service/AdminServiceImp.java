package com.blog.service;

import com.blog.MyBatisUtil;
import com.blog.dao.AdminMapper;
import com.blog.other.Tool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AdminServiceImp implements AdminService {

    @Autowired
    private Tool tool;
    /*
     * @Description 新增博客
     * @Author 284668461@qq.com
     * @Date 16:19 2020/4/27
     * @Param [hm]
     * @return int
     **/
    @Override
    public int insertBolg(Map hm) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);

        int resNum = m.insertBlog(hm);
        session.commit();
        return resNum;
    }

    /*
     * @Description 插入博客分类
     * @Author 284668461@qq.com
     * @Date 16:19 2020/4/27
     * @Param [blogId, classify]
     * @return int
     **/
    @Override
    public int insertBlogClassify(int blogId, String classify) {


        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);

        int resNum = m.insertBlogClassify(blogId,tool.wipeOffStr(classify));
        session.commit();
        return resNum;
    }


    /*
     * @Description 插入博客标签
     * @Author 284668461@qq.com
     * @Date 16:20 2020/4/27
     * @Param [blogId, Tag]
     * @return int
     **/
    @Override
    public int insertBlogTag(int blogId,String[] arr) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);

        int resNum = m.insertBlogTag(blogId,arr);

        session.commit();

        return resNum;

    }



    /*
     * @Description 查询博客id
     * @Author 284668461@qq.com
     * @Date 16:20 2020/4/27
     * @Param [hm]
     * @return int
     **/
    @Override
    public int selectBolgId(Map hm) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);

        int resNum = m.selectBolgId(hm);

        return resNum;

    }


    /*
     * @Description 删除博客
     * @Author 284668461@qq.com
     * @Date 17:48 2020/4/28
     * @Param [blogId]
     * @return int
     **/
    @Override
    public int delBlog(int blogId) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);
        int delNum = m.delBlog(blogId);
        session.commit();
        return delNum;
    }



    /*
     * @Description 查询标签是否已存在
     * @Author 284668461@qq.com
     * @Date 10:40 2020/4/29
     * @Param [Tag]
     * @return int
     **/
    @Override
    public int queryTag(String Tag) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);
        return  m.queryTag(Tag);

    }



    /*
     * @Description 新增标签
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [Tag]
     * @return int
     **/
    @Override
    public int insertTag(String Tag) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);
        int dataNum = m.insertTag(Tag);

        session.commit();
        return dataNum;
    }

    /*
     * @Description 新增分类
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [classify]
     * @return int
     **/
    @Override
    public int insertClassify(String classify) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);
        int dataNum = m.insertClassify(classify);

        session.commit();
        return dataNum;

    }


    /*
     * @Description 查询分类是否存在
     * @Author 284668461@qq.com
     * @Date 10:42 2020/4/29
     * @Param [classify]
     * @return int
     **/
    @Override
    public int queryClassify(String classify) {
        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);
        return  m.queryClassify(classify);
    }



    /*
     * @Description 插入博客版权信息
     * @Author 284668461@qq.com
     * @Date 21:08 2020/5/8
     * @Param [blogId, copyrightFlag, author, path]
     * @return int
     **/
    public int insertCopyright(int blogId,String copyrightFlag,String author,String path){

        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);

        int dataNum ;
        if(copyrightFlag.equals("原创")){

             dataNum =  m.insertCopyright(blogId,0,author,path);

        }else if(copyrightFlag.equals("转载")){

            dataNum = m.insertCopyright(blogId,1,author,path);

        }else{

            dataNum =  m.insertCopyright(blogId,2,author,path);

        }

        session.commit();
        return dataNum;

    }
}
