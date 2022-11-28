package com.devoralime.filemanagger.storage.model;


import com.devoralime.filemanagger.storage.enums.SecurityTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class StorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "security_type")
    @Enumerated(value = EnumType.STRING)
    private SecurityTypeEnum securityType;



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
