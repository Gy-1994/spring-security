package com.gy.security.service;



import com.gy.security.domain.User;

import java.util.List;

public interface UserService {
    //查询所有用户
    public List<User> getUserList();
    //根据id查询
    public User findUserById(long id);
    //保存用户
    public void save(User user);
    //编辑用户
    public void edit(User user);
    //删除用户
    public void delete(Long id);

}
