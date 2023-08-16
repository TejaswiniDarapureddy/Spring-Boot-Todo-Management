package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
//We can use swagger annotations to provide the information about the REST API's
//This tag annotation is used to provide the information about the user resource
@Tag(
        name = "CRUD REST API for user resource",
        description = "CRUD REST API's - Create User, Update User, Get User, Get All Users, delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //This Operation annotation provides the summary about each REST API, what this REST API is for
    @Operation(
            summary = "Create user REST API",
            description = "This create user REST API is used to save the user to database"
    )
    //This annotation is used to return response the API will send.
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser=userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    //This Operation annotation provides the summary about each REST API, what this REST API is for
    @Operation(
            summary = "Get user REST API",
            description = "This Get user REST API is used to return the single user based on given ID from database"
    )
    //This annotation is used to return response the API will send.
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable("id") Long userId){
        UserDto user= userService.getUserByID(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //This Operation annotation provides the summary about each REST API, what this REST API is for
    @Operation(
            summary = "Get All users REST API",
            description = "This Get all users REST API is used to return all the users from database"
    )
    //This annotation is used to return response the API will send.
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    //This Operation annotation provides the summary about each REST API, what this REST API is for
    @Operation(
            summary = "Update user REST API",
            description = "This update user REST API is used to update the details of the user based on given ID and saves it to the database"
    )
    //This annotation is used to return response the API will send.
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build update user RESTAPI
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id")Long userId, @Valid @RequestBody UserDto user){
        user.setId(userId);
        UserDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    //This Operation annotation provides the summary about each REST API, what this REST API is for
    @Operation(
            summary = "Delete user REST API",
            description = "This Delete user REST API is used to delete the user based on given ID from database"
    )
    //This annotation is used to return response the API will send.
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted Successfully!",HttpStatus.OK);
    }


}
