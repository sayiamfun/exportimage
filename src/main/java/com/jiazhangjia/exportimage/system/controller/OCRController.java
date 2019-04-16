package com.jiazhangjia.exportimage.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Api("图片识别")
@CrossOrigin
@RestController
public class OCRController {

    @ApiOperation("提取文字")
    @PostMapping("getText")
    public Object getText(@RequestBody MultipartFile file) {
        try {
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

}
