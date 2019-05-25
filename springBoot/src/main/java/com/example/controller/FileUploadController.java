package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/9/5.
 */
@RestController
@Slf4j
public class FileUploadController {

    @PostMapping("/upload")
    public  String upload(@RequestParam MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return  "上传文件不能为空";
        }
        // 文件类型
        String contentType = file.getContentType();
        String fileName = file.getName();
        log.info("服务器文件名：" + fileName);
        // 原文件名即上传的文件名
        String origFileName = file.getOriginalFilename();
        // 文件大小
        Long fileSize = file.getSize();
        file.transferTo(new File("d://okong-" + origFileName));
        return String.format(file.getClass().getName() + "方式文件上传成功！\n文件名:%s,文件类型:%s,文件大小:%s", origFileName, contentType,fileSize);
    }
}
