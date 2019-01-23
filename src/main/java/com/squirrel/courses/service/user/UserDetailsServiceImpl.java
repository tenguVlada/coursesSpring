package com.squirrel.courses.service.user;

import com.squirrel.courses.dataaccess.dao.user.JdbcUserDAO;
import com.squirrel.courses.dataaccess.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcUserDAO jdbcUserDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = this.jdbcUserDAO.findByLogin(login);

        if (user == null) {
            System.out.println("User not found! " + login);
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        }

        System.out.println("Found User: " + user);

        // [ROLE_USER, ROLE_ADMIN,..]
        /*List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }*/

        UserDetails userDetails = (UserDetails) new User(user.getLogin(), //
                user.getHashPass(), user.getRole(), user.getUserName(), user.getDescription());

        return userDetails;
    }
}
