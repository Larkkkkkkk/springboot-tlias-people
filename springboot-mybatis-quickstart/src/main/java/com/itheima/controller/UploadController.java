package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.font.MultipleMaster;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //文件上传 --存储到本地磁盘
//    @PostMapping("/upload")
//    public Result upload(String username, String age, MultipartFile image ) throws Exception {  //服务器端接受文件 MultipartFile
//        log.info("文件上传:{}",image);
//        //1.获取原始文件名字
//        String originalFilename = image.getOriginalFilename();
//
//        //2.构造新的文件名(保证唯一不能重复，可能存在一张图片上传多次)  -- UUID(随机生成)+文件名称(截取名字)
//        //获取.png .jpg等等的最后一个.位置
//        int index=originalFilename.lastIndexOf(".");
//        //截取前面的名称
//        String extname=originalFilename.substring(index);
//        String newName=UUID.randomUUID().toString()+extname;
//
//        //2.将文件存储到服务器的磁盘目录中
//        image.transferTo(new File("F:\\qycache\\"+newName));  //一定要保证文件夹存在
//
//        return Result.success();
//    }


    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        //调用阿里云OSS工具类进行文件上传
        String url=AliOSSUtils.upload(file);

        //返回正确状态和图片地址string
        return Result.success(url);
    }

}
