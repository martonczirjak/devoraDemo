package com.devoralime.filemanagger.storage.service;

import com.devoralime.filemanagger.storage.dto.CreateStorageDto;
import com.devoralime.filemanagger.storage.mapper.StorageMapper;
import com.devoralime.filemanagger.storage.model.StorageEntity;
import com.devoralime.filemanagger.storage.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StorageService implements IStorageService{

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public StorageEntity create(CreateStorageDto dto) {
        return storageRepository.save(storageMapper.toEntity(dto));
    }

    @Override
    public List<StorageEntity> findAll() {
        return storageRepository.findAll();
    }

    public StorageRepository getStorageRepository() {
        return storageRepository;
    }

    public void setStorageRepository(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }
}
