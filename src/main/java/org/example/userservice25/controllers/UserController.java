package org.example.userservice25.controllers;


import org.example.userservice25.dtos.LoginRequestDTO;
import org.example.userservice25.dtos.SignUpRequestDTO;
import org.example.userservice25.dtos.TokenDTO;
import org.example.userservice25.dtos.UserDTO;
import org.example.userservice25.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.example.userservice25.models.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        User user =  userService.signUp(
                signUpRequestDTO.getName(),
                signUpRequestDTO.getEmail(),
                signUpRequestDTO.getPassword()
        );

        return UserDTO.fromUser(user);
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDTO validateToken(@PathVariable("tokenValue") String tokenValue) {
        return null;
    }

    public void logOut(){}

}
