package com.vlasova.task4.dao;

import com.vlasova.task4.entity.Authorities;

public interface AuthoritiesDao {
    Authorities findRoleByName(String theRoleName);
}