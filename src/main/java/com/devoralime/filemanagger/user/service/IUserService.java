package com.devoralime.filemanagger.user.service;

import com.devoralime.filemanagger.upload.model.UploadedFileEntity;
import com.devoralime.filemanagger.user.dto.CreateUserDto;
import com.devoralime.filemanagger.user.dto.CreateUserStorageCredentialDto;
import com.devoralime.filemanagger.user.model.DevoraUserEntity;
import com.devoralime.filemanagger.user.model.UserStorageCredentialEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;

public interface IUserService extends UserDetailsService {

    DevoraUserEntity create(CreateUserDto dto);

    List<DevoraUserEntity> findAll();

    void createUserStorageCredential(CreateUserStorageCredentialDto dto);
    void removeUserStorageCredential(Long id);
    DevoraUserEntity getCurrentUser();

    List<UploadedFileEntity> getUploadedFiles(String fileName, LocalDate uploadDate, Long storageId);

    void addFriend(Long friendId);

    List<UserStorageCredentialEntity> getStorageCredentials(Long userId);
}
