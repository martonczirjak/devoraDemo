package com.devoralime.filemanagger.storage.mapper;

import com.devoralime.filemanagger.config.MappingConfig;
import com.devoralime.filemanagger.storage.dto.CreateStorageDto;
import com.devoralime.filemanagger.storage.dto.StorageDetailDto;
import com.devoralime.filemanagger.storage.model.StorageEntity;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface StorageMapper {
   StorageDetailDto toDto(StorageEntity entity);

    StorageEntity toEntity(CreateStorageDto dto);
}
