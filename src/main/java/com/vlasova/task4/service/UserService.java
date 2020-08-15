package com.vlasova.task4.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.vlasova.task4.entity.User;
import com.vlasova.task4.user.CrmUser;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    User getUser(int id);
    List<User> getUsers();
    void delete (int id);
    void save(CrmUser crmUser);
    void save(User user);
}