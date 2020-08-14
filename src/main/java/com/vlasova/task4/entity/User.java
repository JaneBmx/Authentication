package com.vlasova.task4.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String userName;

    @Column(name = "last_online")
    private Timestamp lastOnline;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "email")
    private String email;

    @Column(name = "enable")
    private boolean enabled;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "authorities_id")
    private Authorities authorities;

    public User() {
    }

    public User(String userName, String password, Timestamp lastOnline, Date registerDate, String email, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.lastOnline = lastOnline;
        this.registerDate = registerDate;
        this.email = email;
        this.enabled = enabled;
    }

    public User(String userName, String password, Timestamp lastOnline, Date registerDate, String email, Authorities authorities) {
        this.userName = userName;
        this.password = password;
        this.lastOnline = lastOnline;
        this.registerDate = registerDate;
        this.email = email;
        this.authorities = authorities;
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

    public Timestamp getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Timestamp lastOnline) {
        this.lastOnline = lastOnline;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }
}