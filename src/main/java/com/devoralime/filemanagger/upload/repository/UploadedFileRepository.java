package com.devoralime.filemanagger.upload.repository;

import com.devoralime.filemanagger.storage.model.StorageEntity;
import com.devoralime.filemanagger.upload.model.UploadedFileEntity;
import com.devoralime.filemanagger.user.model.DevoraUserEntity;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFileEntity, Long> {

    @Query("SELECT U FROM UploadedFileEntity U WHERE U.uploader IN :users AND U.fileName like :text AND U.storage = :storage AND U.uploadDate =:uploadDate")
    List<UploadedFileEntity> getUploadedFileList(List<DevoraUserEntity> users, String text, StorageEntity storage, LocalDate uploadDate);
}
