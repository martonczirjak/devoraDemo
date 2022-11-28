package com.devoralime.filemanagger.user.repository;

import com.devoralime.filemanagger.user.model.DevoraUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevoraUserRepository extends JpaRepository<DevoraUserEntity,Long> {

    Optional<DevoraUserEntity> findByUserName(String userName);
}
