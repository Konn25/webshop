package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.UsersDTO;
import com.webshop.Webshop.jpa.Users;
import com.webshop.Webshop.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable("id") Long id) {
        return usersService.getUsersById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewUser(@RequestBody UsersDTO usersDTO) {

        Users usersRequest = modelMapper.map(usersDTO, Users.class);

        Users newUsers = usersService.createNewUsers(usersRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New User added to the database! ID: " + newUsers.getId());

    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UsersDTO usersDTO) {

        Users usersRequest = modelMapper.map(usersDTO, Users.class);

        Users newUsers = usersService.updateUsers(usersRequest);

        return ResponseEntity.status(HttpStatus.OK)
                             .body("User updated! ID: " + newUsers.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUsersById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Users deleted!!");
    }

}
