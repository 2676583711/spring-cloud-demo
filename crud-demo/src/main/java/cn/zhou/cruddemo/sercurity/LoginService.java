package cn.zhou.cruddemo.sercurity;

import cn.zhou.cruddemo.dao.UserDao;
import cn.zhou.cruddemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("login.username=" + s);
        User user = userDao.findUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getNickname());
        grantedAuthorities.add(simpleGrantedAuthority);


        //true, true, true, true,
        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), grantedAuthorities);

        return user1;
    }


}
