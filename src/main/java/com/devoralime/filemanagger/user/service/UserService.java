package com.devoralime.filemanagger.user.service;

import com.devoralime.filemanagger.exception.CredentialExistException;
import com.devoralime.filemanagger.exception.InvalidCredentialFormatException;
import com.devoralime.filemanagger.storage.model.StorageEntity;
import com.devoralime.filemanagger.storage.repository.StorageRepository;
import com.devoralime.filemanagger.upload.model.UploadedFileEntity;
import com.devoralime.filemanagger.upload.repository.UploadedFileRepository;
import com.devoralime.filemanagger.user.dto.CreateUserDto;
import com.devoralime.filemanagger.user.dto.CreateUserStorageCredentialDto;
import com.devoralime.filemanagger.user.mapper.UserMapper;
import com.devoralime.filemanagger.user.model.DevoraUserEntity;
import com.devoralime.filemanagger.user.model.UserStorageCredentialEntity;
import com.devoralime.filemanagger.user.repository.DevoraUserRepository;
import com.devoralime.filemanagger.user.repository.UserStorageCredentialRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IUserService {

    private final DevoraUserRepository userRepository;
    private final UserStorageCredentialRepository userStorageCredentialRepository;
    private final StorageRepository storageRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final UploadedFileRepository uploadedFileRepository;

    @Autowired
    public UserService(DevoraUserRepository userRepository, UserStorageCredentialRepository userStorageCredentialRepository, StorageRepository storageRepository, UserMapper userMapper, BCryptPasswordEncoder encoder, UploadedFileRepository uploadedFileRepository) {
        this.userRepository = userRepository;
        this.userStorageCredentialRepository = userStorageCredentialRepository;
        this.storageRepository = storageRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public DevoraUserEntity create(CreateUserDto dto) {
        DevoraUserEntity e = new DevoraUserEntity();
        e.setUserName(dto.getUserName());
        e.setPassword(encoder.encode(dto.getPassword()));
        return userRepository.save(e);
    }

    @Override
    public List<DevoraUserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void createUserStorageCredential(CreateUserStorageCredentialDto dto) {
        StorageEntity storage = storageRepository.findById(dto.getStorageId()).orElseThrow(() -> new ObjectNotFoundException(dto.getStorageId(), "Storage"));
        if (userStorageCredentialRepository.findByDevoraUserAndStorage(getCurrentUser(), storage).isPresent()) {
            throw new CredentialExistException("This type of credential already exist for this user.");
        }
        switch (storage.getSecurityType()) {
            case BASIC_AUTH: {
                if (!StringUtils.isBlank(dto.getPassword()) && !StringUtils.isBlank(dto.getUserName())) {
                    UserStorageCredentialEntity userStorageCredentialEntity = new UserStorageCredentialEntity();
                    userStorageCredentialEntity.setUserName(dto.getUserName());
                    userStorageCredentialEntity.setPassword(dto.getPassword());
                    userStorageCredentialEntity.setStorage(storage);
                    userStorageCredentialEntity.setDevoraUser(getCurrentUser());
                    userStorageCredentialRepository.save(userStorageCredentialEntity);
                } else {
                    throw new InvalidCredentialFormatException("Invalid credential format");
                }
                break;
            }
            case TOKEN: {
                if (!StringUtils.isBlank(dto.getToken())) {
                    UserStorageCredentialEntity userStorageCredentialEntity = new UserStorageCredentialEntity();
                    userStorageCredentialEntity.setStorage(storage);
                    userStorageCredentialEntity.setDevoraUser(getCurrentUser());
                    userStorageCredentialEntity.setToken(dto.getToken());
                    userStorageCredentialRepository.save(userStorageCredentialEntity);
                } else {
                    throw new InvalidCredentialFormatException("Invalid credential format");
                }
                break;
            }
        }
    }

    @Override
    public void removeUserStorageCredential(Long id) {
        UserStorageCredentialEntity crendential = userStorageCredentialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Crendential"));
        if (crendential.getDevoraUser().getUserName().equals(getCurrentUser().getUserName())) {
            userStorageCredentialRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id, "Crendential");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DevoraUserEntity devoraUserEntity = userRepository.findByUserName(username).orElseThrow(() -> new ObjectNotFoundException(username, "User"));
        return new User(devoraUserEntity.getUserName(), devoraUserEntity.getPassword(), new ArrayList<>());
    }

    @Override
    public DevoraUserEntity getCurrentUser() {
        String s = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<DevoraUserEntity> byUserName = userRepository.findByUserName(s);
        return byUserName.get();
    }

    @Override
    public List<UploadedFileEntity> getUploadedFiles(String fileName, LocalDate uploadDate, Long storageId) {
        StorageEntity storage = storageRepository.findById(storageId).orElseThrow(() -> new ObjectNotFoundException(storageId, "Storage"));
        DevoraUserEntity currentUser = getCurrentUser();
        List<DevoraUserEntity> users = new ArrayList<>();
        users.add(currentUser);
        users.addAll(currentUser.getFriends());
        return uploadedFileRepository.getUploadedFileList(users,fileName,storage,uploadDate);
    }

    @Override
    public void addFriend(Long friendId) {
        DevoraUserEntity friend = userRepository.findById(friendId).orElseThrow(() -> new ObjectNotFoundException(friendId, "User"));
        DevoraUserEntity currentUser = getCurrentUser();
        currentUser.getFriends().add(friend);
        userRepository.save(currentUser);

    }

    @Override
    public List<UserStorageCredentialEntity> getStorageCredentials(Long userId) {
        return userStorageCredentialRepository.findByUserId(userId);
    }
}
