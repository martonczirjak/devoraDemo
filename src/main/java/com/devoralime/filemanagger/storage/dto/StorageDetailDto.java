package com.devoralime.filemanagger.storage.dto;


import com.devoralime.filemanagger.storage.enums.SecurityTypeEnum;

public class StorageDetailDto {
    private Long id;
    private String name;
    private SecurityTypeEnum securityType;


    public StorageDetailDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
