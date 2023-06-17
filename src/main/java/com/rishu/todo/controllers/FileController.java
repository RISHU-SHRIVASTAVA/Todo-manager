package com.rishu.todo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {
    Logger logger= LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single")
    public String uploadSingle(@RequestParam("image")MultipartFile file){
       logger.info("name {}",file.getName());
       logger.info("Content Type {}",file.getContentType());
       logger.info("Filename {}",file.getOriginalFilename());
       logger.info("size {}",file.getSize());
//       logger.info("Inputstream {}",file.getInputStream());
        return "File Test";
    }
    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files")MultipartFile[] files){
        Arrays.stream(files).forEach(file ->{

       logger.info("name {}",file.getName());
       logger.info("Content Type {}",file.getContentType());
       logger.info("Filename {}",file.getOriginalFilename());
       logger.info("size {}",file.getSize());
//       logger.info("Inputstream {}",file.getInputStream());
        });
        return "Multiple file";
    }

    //serving image files in response
    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response){
        //image
        try{
            InputStream fileInputStream = new FileInputStream("images/Screenshot_1.jpg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());
        }
        catch(Exception e){
           e.printStackTrace();
        }

    }

}
