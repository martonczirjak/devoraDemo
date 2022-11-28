package com.devoralime.filemanagger.upload.model;

import com.devoralime.filemanagger.storage.model.StorageEntity;
import com.devoralime.filemanagger.user.model.DevoraUserEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "uploaded_file")
public class UploadedFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private DevoraUserEntity uploader;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private StorageEntity storage;

    @Column(name = "upload_date")
    public LocalDate uploadDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DevoraUserEntity getUploader() {
        return uploader;
    }

    public void setUploader(DevoraUserEntity uploader) {
        this.uploader = uploader;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public StorageEntity getStorage() {
        return storage;
    }

    public void setStorage(StorageEntity storage) {
        this.storage = storage;
    }
}
