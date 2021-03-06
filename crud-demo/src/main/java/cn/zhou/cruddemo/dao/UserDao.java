package cn.zhou.cruddemo.dao;


import cn.zhou.cruddemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

/*

这里加的@Mapper是 MyBatis的备注，目的是为了让spring能够根据xml和
这个接口动态生成这个接口的实现。
如果是加@Repository，就是spring生成一个bean，自动注入service的相关引用中。
 */
@Mapper
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findAll();

    User findUserByUsername(String username);

    List<User> findUsersByNicknameLike(String nickname);

    List<User> findUsersByUsernameLike(String username);

    List<User> findUsersByPasswordLike(String username);

    List<User> findUsersBySexLike(String username);


}
