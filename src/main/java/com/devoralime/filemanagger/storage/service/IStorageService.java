package com.devoralime.filemanagger.storage.service;

import com.devoralime.filemanagger.storage.dto.CreateStorageDto;
import com.devoralime.filemanagger.storage.dto.StorageDetailDto;
import com.devoralime.filemanagger.storage.model.StorageEntity;

import java.util.List;

public interface IStorageService {

    StorageEntity create(CreateStorageDto dto);

    List<StorageEntity> findAll();
}
