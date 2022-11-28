package com.devoralime.filemanagger.storage.dto;


import com.devoralime.filemanagger.storage.enums.SecurityTypeEnum;
import com.devoralime.filemanagger.storage.model.StorageEntity;

import javax.validation.constraints.NotNull;

public class CreateStorageDto {

    @NotNull
    private String name;
    @NotNull
    private SecurityTypeEnum securityType;


    public CreateStorageDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SecurityTypeEnum getSecurityType() {
        return securityType;
    }

    public void setSecurityType(SecurityTypeEnum securityType) {
        this.securityType = securityType;
    }
}
