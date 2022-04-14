package com.volunteer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class UploadUtil {

    /**
     * 上传文件
     * @param files
     * @return
     * @throws IOException
     */
    public static List<String> upload(MultipartFile[] files) throws IOException {
        Map<String, Object> res = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = "http://39.99.34.198/picture/";
            String randomName = UUID.randomUUID().toString().replace("-", "");

            String originName = file.getOriginalFilename();

            String fname = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            randomName += fname;
            fname = randomName;

            String pic = ".+(.png|.jpg|.gif|.bmp|.psd|.tiff|.tga|.eps)$";

            // match images
            Pattern compile = Pattern.compile(pic);
            Matcher matcher = compile.matcher(fname);

            if (matcher.matches()) {

                File target = new File("/root/picture/"+fname);
                if(!target.exists()){
                    target.createNewFile();
                }
                Path path1 = target.toPath();
                try {
                    Files.write(path1,file.getBytes());
                } catch (Exception e) {

                    return null;
                }
                url +=fname;
                log.info("url: {}",url);

            }
            list.add(url);

        }
        return list;
    }
}
