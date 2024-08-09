package com.webshop.Webshop.dao;


import com.webshop.Webshop.jpa.Users;
import com.webshop.Webshop.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.webshop.Webshop.config.Roles.*;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UsersService usersService;

    public UserDetails findUserByEmail(String email) {

        List<UserDetails> APP_USERS = getAllUserFromDatabase();

        return APP_USERS.stream().filter(user -> user.getUsername().equals(email)).findFirst()
                        .orElseThrow(() -> new UsernameNotFoundException("User not found in the database!"));

    }

    public List<UserDetails> getAllUserFromDatabase() {

        List<UserDetails> userList = new ArrayList<>();

        List<Users> allUsers = usersService.getAllUsers();

        for (Users client : allUsers) {
            if (client.isAdmin()) {
                userList.add(new User(client.getEmail(), client.getPassword(),
                                      Collections.singleton(new SimpleGrantedAuthority(ROLE_ADMIN.name()))));
            }
            else {
                userList.add(new User(client.getEmail(), client.getPassword(),
                                      Collections.singleton(new SimpleGrantedAuthority(ROLE_USER.name()))));
            }
        }

        return userList;
    }

}
