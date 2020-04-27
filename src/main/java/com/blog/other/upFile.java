package com.blog.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

/*
 * @Description 上传文件类
 * @Author 284668461@qq.com
 * @Date 22:10 2020/4/26
 **/
@Configuration
public class upFile {


    /*
     * @Description 上传图片
     * @Author 284668461@qq.com
     * @Date 22:18 2020/4/26
     * @Param [file, path, showPath]
     * @return java.lang.String
     **/

    public String upImg(MultipartFile file){

        String path = "src/main/resources/static/files/";
        String showPath = "/Path/img/";


        //得到文件名称
        String fileName = file.getOriginalFilename();

        //得到文件后缀名
        String prefix = fileName.substring(fileName.lastIndexOf('.') + 1);

        //得到当前时间戳
        Long  time = Calendar.getInstance().getTimeInMillis();


        String imgName= time+"."+prefix;
        String filePath = path+ imgName;


        File targetFile = new File(path);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(filePath );
            out.write(file.getBytes());
            out.flush();
            out.close();


//          前端显示路径
            String  resPath = "/Path/img/"+imgName;
            return resPath;

        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }
}
