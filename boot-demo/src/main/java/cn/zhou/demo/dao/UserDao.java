package cn.zhou.demo.dao;


import cn.zhou.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*

这里加的@Mapper是 MyBatis的备注，目的是为了让spring能够根据xml和
这个接口动态生成这个接口的实现。
如果是加@Repository，就是spring生成一个bean，自动注入service的相关引用中。
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findAll();


    User findUserByUsername(String username);

    List<User> findUsersByNicknameLike(String nickname);
}
