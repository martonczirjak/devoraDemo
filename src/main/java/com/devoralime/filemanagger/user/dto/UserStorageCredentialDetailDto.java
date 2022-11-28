package com.devoralime.filemanagger.user.dto;

public class UserStorageCredentialDetailDto {
    private Long id;
    private String storageName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }
}
