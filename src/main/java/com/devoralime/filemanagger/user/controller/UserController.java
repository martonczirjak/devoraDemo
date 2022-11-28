package com.devoralime.filemanagger.user.controller;

import com.devoralime.filemanagger.upload.dto.UploadedFileDto;
import com.devoralime.filemanagger.upload.model.UploadedFileEntity;
import com.devoralime.filemanagger.user.dto.CreateUserDto;
import com.devoralime.filemanagger.user.dto.CreateUserStorageCredentialDto;
import com.devoralime.filemanagger.user.dto.UserDetailDto;
import com.devoralime.filemanagger.user.dto.UserStorageCredentialDetailDto;
import com.devoralime.filemanagger.user.mapper.UserMapper;
import com.devoralime.filemanagger.user.service.IUserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(path = "/register")
    public UserDetailDto create(@Valid @RequestBody CreateUserDto dto) {
        return userMapper.toDto(userService.create(dto));
    }

    @GetMapping
    public List<UserDetailDto> userList() {
        return userService.findAll().stream().map(it -> userMapper.toDto(it)).collect(Collectors.toList());
    }

    @PutMapping(path = "/userStorage")
    public void createUserStorage(@Valid @RequestBody CreateUserStorageCredentialDto dto) {
        userService.createUserStorageCredential(dto);
    }

    @DeleteMapping(path = "/userStorage")
    public void removeUserStorage(@RequestParam Long id) {
        userService.removeUserStorageCredential(id);
    }

    @GetMapping(path = "/myStorageCredentials")
    public List<UserStorageCredentialDetailDto> myStorageCredentials() {
        return userService.getStorageCredentials(userService.getCurrentUser().getId()).stream().map(it -> {
            UserStorageCredentialDetailDto userStorageCredentialDetailDto = new UserStorageCredentialDetailDto();
            userStorageCredentialDetailDto.setStorageName(it.getStorage().getName());
            userStorageCredentialDetailDto.setId(it.getId());
            return userStorageCredentialDetailDto;
        }).collect(Collectors.toList());
    }

    @GetMapping(path = "/uploadedFiles")
    public List<UploadedFileDto> getUploadedFiles(
            @RequestParam(name = "fileName") String fileName,
            @RequestParam(name = "uploadDate") @DateTimeFormat(pattern = "YYYY-MM-DD") String uploadDate,
            @RequestParam(name = "storageId") Long storageId) {
        List<UploadedFileEntity> uploadedFiles = userService.getUploadedFiles(fileName, LocalDate.parse(uploadDate), storageId);
        return uploadedFiles.stream().map(it -> {
            UploadedFileDto uploadedFileDto = new UploadedFileDto();
            uploadedFileDto.setFileName(it.getFileName());
            uploadedFileDto.setUploadDate(it.getUploadDate());
            uploadedFileDto.setStorageName(it.getStorage().getName());
            return uploadedFileDto;
        }).collect(Collectors.toList());
    }

    @PutMapping("/addFriend")
    public void addFriend(@RequestParam(name = "friendId") Long friendId) {
        userService.addFriend(friendId);
    }


}
