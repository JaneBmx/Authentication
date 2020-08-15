package com.vlasova.task4.dao;

import com.vlasova.task4.entity.User;

import java.util.List;

public interface UserDao {
    User findByUserName(String userName);
    User getUser(int id);
    List<User> getUsers();
    void save(User user);
    void delete(int id);
}