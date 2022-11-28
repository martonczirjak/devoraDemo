package com.devoralime.filemanagger.user.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateUserDto {
    @NotNull
    @NotEmpty
    private String userName;
    @NotNull
    @NotEmpty
    private String password;

    public CreateUserDto() {
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
}
