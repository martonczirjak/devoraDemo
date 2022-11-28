package com.devoralime.filemanagger.storage.repository;

import com.devoralime.filemanagger.storage.model.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity,Long> {
}
