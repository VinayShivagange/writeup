package com.writeup.domain;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;

    @Column(name = "DISPLAYNAME", nullable = false)
    private String displayName;

    @Column(name = "USERNAME", nullable = false)
    private String userName;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String displayName, String userName) {
        this.id = id;
        this.displayName = displayName;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", userName=" + userName +
                '}';
    }
}