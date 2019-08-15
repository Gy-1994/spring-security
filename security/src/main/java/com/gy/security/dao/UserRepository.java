package com.gy.security.dao;


import com.gy.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//继承JpaRepository会自动实现很多内置方法
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}
