package cn.zhou.cruddemo.service.impl;

import cn.zhou.cruddemo.dao.UserDao;
import cn.zhou.cruddemo.pojo.User;
import cn.zhou.cruddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("查询params::" + user);
        if (user != null) {
            List arrayList = new ArrayList();
            if (user.getId() != null) {
                Object one = userDao.getOne(user.getId());
                arrayList.add(one);
                return arrayList;
            }
            if (user.getUsername() != null) {
                List<User> userByUsername = userDao.findUsersByUsernameLike(user.getUsername());
                if (userByUsername.size() > 0) {
                    return userByUsername;
                } else {
                    userByUsername = userDao.findUsersByNicknameLike(user.getUsername());
                    if (userByUsername.size() > 0) {
                        return userByUsername;
                    } else {
                        userByUsername = userDao.findUsersByPasswordLike(user.getUsername());
                        if (userByUsername.size() > 0) {
                            return userByUsername;
                        } else {
                            userByUsername = userDao.findUsersBySexLike(user.getUsername());
                            if (userByUsername.size() > 0) {
                                return userByUsername;
                            }
                        }
                    }
                }
            }
        }
        return userDao.findAll();
    }
}
