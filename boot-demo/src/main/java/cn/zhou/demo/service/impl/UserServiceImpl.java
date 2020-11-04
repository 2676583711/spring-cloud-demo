package cn.zhou.demo.service.impl;

import cn.zhou.demo.dao.UserDao;
import cn.zhou.demo.pojo.User;
import cn.zhou.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static cn.zhou.demo.utils.MyUtil.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public boolean addUser(User user) {
        Object save = userDao.save(user);
        return save != null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean addUser(List<User> users) {
        List list = userDao.saveAll(users);
        return list.size() == users.size();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public boolean deleteUser(User user) {
        try {
            userDao.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteUser(List<User> users) {
        try {
            userDao.deleteInBatch(users);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean updateUser(User user) {
        Object o = userDao.saveAndFlush(user);
        return o != null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean updateUser(List<User> users) {
        List list = userDao.saveAll(users);
        return list.size() == users.size();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userDao.findAll();
        return all;
    }

    @Override
    public List<User> findUser(User user) {

        if (isNotNull(user)) {
            List arrayList = new ArrayList();
            if (isNotNull(user.getId())) {
                Object one = userDao.getOne(user.getId());
                arrayList.add(one);
                return arrayList;
            }
            if (isNotNull(user.getUsername())) {
                List<User> userByUsername = userDao.findUsersByNicknameLike(user.getUsername());
                return userByUsername;
            }
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        if (isNull(username)){
            return null;
        }
        return userDao.findUserByUsername(username);

    }

}

