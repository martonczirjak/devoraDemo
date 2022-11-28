package com.devoralime.filemanagger.upload.service;

import com.devoralime.filemanagger.upload.dto.UploadIdsDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IUploadService {

    void upload(MultipartFile file, List<Long> ids);
}
