package com.devoralime.filemanagger.user.mapper;

import com.devoralime.filemanagger.config.MappingConfig;
import com.devoralime.filemanagger.user.dto.CreateUserDto;
import com.devoralime.filemanagger.user.dto.UserDetailDto;
import com.devoralime.filemanagger.user.model.DevoraUserEntity;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface UserMapper {
    UserDetailDto toDto(DevoraUserEntity entity);

    DevoraUserEntity toEntity(CreateUserDto dto);
}
