package cn.zhou.cruddemo.service;

import cn.zhou.cruddemo.pojo.User;

import javax.transaction.Transactional;
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

}
