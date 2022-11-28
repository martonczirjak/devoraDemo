package com.devoralime.filemanagger.user.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devora_user")
public class DevoraUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;


    @ManyToMany
    @JoinTable(name="devora_friend",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="friend_id")
    )
    private List<DevoraUserEntity> friends;

    @ManyToMany
    @JoinTable(name="devora_friend",
            joinColumns=@JoinColumn(name="friend_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<DevoraUserEntity> friendOf;


    @OneToMany(
            mappedBy = "devoraUser", orphanRemoval = true, cascade = CascadeType.ALL
    )
    private List<UserStorageCredentialEntity> storages = new ArrayList<>();


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserStorageCredentialEntity> getStorages() {
        return storages;
    }

    public void setStorages(List<UserStorageCredentialEntity> storages) {
        this.storages = storages;
    }



    public List<DevoraUserEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<DevoraUserEntity> friends) {
        this.friends = friends;
    }

    public List<DevoraUserEntity> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(List<DevoraUserEntity> friendOf) {
        this.friendOf = friendOf;
    }
}
