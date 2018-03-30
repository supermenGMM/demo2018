package com.mm.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.pojo.User;

public interface UserDao extends JpaRepository<User, Serializable>{

}
