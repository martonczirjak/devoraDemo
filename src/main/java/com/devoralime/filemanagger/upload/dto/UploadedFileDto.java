package com.devoralime.filemanagger.upload.dto;

import java.time.LocalDate;
import java.util.Date;

public class UploadedFileDto {
    private String fileName;
    private LocalDate uploadDate;
    private String storageName;


    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
