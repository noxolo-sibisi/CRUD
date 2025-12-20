package com.example.SpringBootApplicationOne.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.models.media.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Everything about User API Application")
public class UserController{
    @Autowired
    private UserService userService;

    @Operation(summary = "Create new User", description = "Adds new user to the system. Returns the created user with a unique ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
                           @ApiResponse(responseCode = "400", description = "Invalid inputs- check validation rules")})

    @PostMapping("/create")
    public String createUser(@Parameter(description = "User object that needs to be added to the system", required = true, schema = @Schema(implementation = User.class))@Valid @RequestBody User userData){
        userService.createUser(userData);
        return    userData.toString() +  "\n Available User(s): " + userService.count();
    }

    @Operation(summary = "Get all Users",description = "View all the users in the system")
    @ApiResponses(value = {@ApiResponse(responseCode ="200", description = "successfully returned List of users in the system",content = @Content(schema = @Schema(implementation = User.class)))})
    @GetMapping("/view")
    public List<User> viewUser(){
     return userService.getAllUsers();
    }

    @Operation(summary = "Get user by ID", description = "Return a single user by ID if found in the system")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User found and returned",content = @Content(schema = @Schema(implementation = User.class))),
    @ApiResponse(responseCode = "404", description = "User not found with the given ID")})

    @GetMapping("/view/{id}")
    public User viewById(@Parameter(description = "Enter the ID of the user to retrieve",example = "1", required = true)@PathVariable Long id){
      return userService.getUserById(id);
    }

    @Operation(summary = "Update User", description = "Update existing user in the system")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User was updated", content = @Content(schema = @Schema(implementation = User.class
    ))),@ApiResponse(responseCode = "204", description = "User was successfully updated",content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
    @ApiResponse(responseCode = "400", description = "the update data is invalid"), @ApiResponse(responseCode = "404", description = "No user exists with the given {id}")})

    @PutMapping("/update/{id}")
    public User updateUser(@Parameter(description = "The ID of the user to be  update",required = true, example = "11")@PathVariable Long id,@Valid @RequestBody User newUser){
      return userService.updateUser(id, newUser);
    }

    @Operation(summary = "Delete user", description = "Delete existing user in the system")
    @ApiResponses(value = {@ApiResponse(responseCode ="204", description = "user was successfully deleted"),@ApiResponse(responseCode = "404", description = "No user exists with the provided {id}"),@ApiResponse(responseCode = "409",
    description = "The user cannot be deleted due to business rules")})

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@Parameter(description = "The {id} of the user to be deleted", example = "111", required = true)@PathVariable Long id) {
      return userService.deleteUserById(id);
    }
}
