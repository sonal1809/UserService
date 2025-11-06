package org.example.userservice25.services;

import org.example.userservice25.models.Token;
import org.example.userservice25.models.User;

public interface UserService {
    User signUp(String name ,String email, String password);

    Token login(String email, String password) throws Exception;

    User validateToken(String tokenValue) throws Exception;

}
