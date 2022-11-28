package com.devoralime.filemanagger.user.model;

import com.devoralime.filemanagger.storage.model.StorageEntity;

import javax.persistence.*;

@Entity
@Table(name = "storage_credential")
public class UserStorageCredentialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private DevoraUserEntity devoraUser;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private StorageEntity storage;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DevoraUserEntity getDevoraUser() {
        return devoraUser;
    }

    public void setDevoraUser(DevoraUserEntity devoraUser) {
        this.devoraUser = devoraUser;
    }

    public StorageEntity getStorage() {
        return storage;
    }

    public void setStorage(StorageEntity storage) {
        this.storage = storage;
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
