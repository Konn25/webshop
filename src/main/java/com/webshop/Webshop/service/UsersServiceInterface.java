package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.ProductType;
import com.webshop.Webshop.jpa.Users;

import java.util.List;
import java.util.Optional;

public interface UsersServiceInterface {

    Optional<Users> getUsersById(Long id);

    void deleteUsersById(Long id);

    Users createNewUsers(Users user);

    Users updateUsers(Users user);

    List<Users> getAllUsers();

}
