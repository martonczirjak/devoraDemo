package com.devoralime.filemanagger.user.dto;

import com.devoralime.filemanagger.storage.enums.SecurityTypeEnum;

import javax.validation.constraints.NotNull;

public class CreateUserStorageCredentialDto {
    @NotNull
    private Long storageId;
    private String userName;
    private String password;
    private String token;


    public CreateUserStorageCredentialDto() {
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
