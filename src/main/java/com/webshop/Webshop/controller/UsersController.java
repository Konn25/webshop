package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.UsersDTO;
import com.webshop.Webshop.jpa.Users;
import com.webshop.Webshop.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearerToken")
@Tag(name = "User API", description = "The User Api")
public class UsersController {

    private final UsersService usersService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all user", description = "Return all user from the database")
    @ApiResponse(responseCode = "200", description = "Get all user")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Return user by id")
    @ApiResponse(responseCode = "200", description = "Get user by id")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<Users> getUserById(@PathVariable("id") Long id) {
        return usersService.getUsersById(id);
    }

    @PostMapping("/new")
    @Operation(summary = "Create new user", description = "Create new user")
    @ApiResponse(responseCode = "201", description = "Create user")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewUser(@RequestBody UsersDTO usersDTO) {

        Users usersRequest = modelMapper.map(usersDTO, Users.class);

        Users newUsers = usersService.createNewUsers(usersRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New User added to the database! ID: " + newUsers.getId());

    }

    @PostMapping("/update")
    @Operation(summary = "Update user", description = "Update user")
    @ApiResponse(responseCode = "200", description = "Update user")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updateUser(@RequestBody UsersDTO usersDTO) {

        Users usersRequest = modelMapper.map(usersDTO, Users.class);

        Users newUsers = usersService.updateUsers(usersRequest);

        return ResponseEntity.status(HttpStatus.OK)
                             .body("User updated! ID: " + newUsers.getId());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user", description = "Delete user by id")
    @ApiResponse(responseCode = "200", description = "Delete user")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUsersById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Users deleted!!");
    }

}
