package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Users;
import com.webshop.Webshop.jpa.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UsersServiceInterface{

    private final UsersRepository usersRepository;

    @Override
    public Optional<Users> getUsersById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public void deleteUsersById(Long id) {
        Optional<Users> foundUser = usersRepository.findById(id);

        if (foundUser.isPresent()){
            usersRepository.deleteById(id);
        }
    }

    @Override
    public Users createNewUsers(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public Users updateUsers(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
