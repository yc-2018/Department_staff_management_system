//仰晨study 创建时间2023/4/23 1:14 星期日
package ikun.study.controller;

import ikun.study.pojo.Result;
import ikun.study.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传:{},{},{}",username,age,image);
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//        //构造一个唯一的文件名（UUID）
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);//扩展名
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新文件名:{}",newFileName);
//
//        //本地存储到E:\Users\Dell\Desktop\
//        image.transferTo(new File("E:\\Users\\Dell\\Desktop\\"+newFileName));
//        return Result.success();
//    }
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传:{}",image.getOriginalFilename());
        //调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,URL为:{}",url);
        return Result.success(url);
    }

}
