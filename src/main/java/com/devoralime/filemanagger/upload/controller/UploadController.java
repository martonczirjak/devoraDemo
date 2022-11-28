package com.devoralime.filemanagger.upload.controller;

import com.devoralime.filemanagger.upload.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void upload(@RequestPart(name = "file") MultipartFile file, @RequestParam(name = "ids") List<Long> ids){
           uploadService.upload(file,ids);
    }
}
