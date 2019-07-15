package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wangjie_fourth on 2019/5/16.
 */
@Slf4j
public class FileUpdateUtil {

    // 图片将写入服务器的路径
    public static final String path = "F:/images/";

    // 项目的域名信息
    public static final String serverAddress = "http://wangjie.natappvip.cc/supershop/";

    // 将图片写入服务器，并返回访问它的路径地址
    public static String writeServerOfImages(MultipartFile productIcon){

        // 获取图片原本的文件名
        String fileName = productIcon.getOriginalFilename();
        //获取图片的扩展名
        String extensionName = StringUtils.substringAfter(fileName, ".");
        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;


        // 写操作
        File newFile = new File(path + newFileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(newFile);
            outputStream.write(productIcon.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return serverAddress + "getImages/" +newFileName;
    }


}
