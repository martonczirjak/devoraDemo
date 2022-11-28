package com.devoralime.filemanagger.user.repository;

import com.devoralime.filemanagger.storage.model.StorageEntity;
import com.devoralime.filemanagger.user.model.DevoraUserEntity;
import com.devoralime.filemanagger.user.model.UserStorageCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStorageCredentialRepository extends JpaRepository<UserStorageCredentialEntity, Long> {

    Optional<UserStorageCredentialEntity> findByDevoraUserAndStorage(DevoraUserEntity devoraUser, StorageEntity storageEntity);

    @Query("SELECT C FROM UserStorageCredentialEntity C WHERE C.devoraUser.id = :userId")
    List<UserStorageCredentialEntity> findByUserId(Long userId);

    @Query("SELECT C FROM UserStorageCredentialEntity C WHERE C.id IN :ids AND C.devoraUser = :user")
    List<UserStorageCredentialEntity> findAllByUserAndIds(List<Long> ids, DevoraUserEntity user);

}
