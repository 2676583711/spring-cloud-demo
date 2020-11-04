package cn.zhou.demo.service;


import cn.zhou.demo.pojo.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);

    boolean addUser(List<User> users);

    boolean deleteUser(User user);

    boolean deleteUser(List<User> users);

    boolean updateUser(User user);

    boolean updateUser(List<User> users);


    List<User> getAllUsers();

    List<User> findUser(User user);

    User findUserByUsername(String username);


}
