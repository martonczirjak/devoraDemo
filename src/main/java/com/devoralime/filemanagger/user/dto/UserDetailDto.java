package com.devoralime.filemanagger.user.dto;

public class UserDetailDto {
    private Long id;
    private String userName;

    public UserDetailDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
