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
     * @Param [blogId, tab]
     * @return int
     **/
    @Override
    public int insertBlogTab(int blogId,String[] arr) {

        SqlSession session = MyBatisUtil.getSessionFactory();

        AdminMapper m = session.getMapper(AdminMapper.class);

        int resNum = m.insertBlogTab(blogId,arr);

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

        int resNum = m.insertBlog(hm);

        return resNum;

    }
}
