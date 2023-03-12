package com.example.User_management_system.dao;

import com.example.User_management_system.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {

}
