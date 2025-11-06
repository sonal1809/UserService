package org.example.userservice25.controllers;


import org.example.userservice25.dtos.LoginRequestDTO;
import org.example.userservice25.dtos.SignUpRequestDTO;
import org.example.userservice25.dtos.TokenDTO;
import org.example.userservice25.dtos.UserDTO;
import org.example.userservice25.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.userservice25.models.User;
import org.example.userservice25.models.Token;

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
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        Token token =  userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        return new ResponseEntity<>(
                TokenDTO.from(token),
                HttpStatus.OK
        );
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDTO validateToken(@PathVariable("tokenValue") String tokenValue) throws Exception {
        User user = userService.validateToken(tokenValue);
        return UserDTO.fromUser(user);
    }

    public void logOut(){}

}
