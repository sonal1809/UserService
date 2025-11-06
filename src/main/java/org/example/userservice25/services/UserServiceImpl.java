package org.example.userservice25.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice25.exceptions.InvalidTokenException;
import org.example.userservice25.exceptions.PasswordMismatchException;
import org.example.userservice25.exceptions.UsernameNotFoundException;
import org.example.userservice25.models.Token;
import org.example.userservice25.models.User;
import org.example.userservice25.repositories.TokenRepository;
import org.example.userservice25.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           TokenRepository tokenRepository
    ){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUp(String name, String email, String password) {
        // Check if the user with same email already exists
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            // Redirect to login page
            return optionalUser.get();
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        // use bcrypt to hash the password before saving
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) throws Exception {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }

        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new PasswordMismatchException("Password not match");
        }

        Token token = new Token();
        token.setUser(user);
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
        token.setExpiryAt(Date.from(Instant.now().plus(30, ChronoUnit.DAYS)));

        return tokenRepository.save(token);
    }


    @Override
    public User validateToken(String tokenValue) throws Exception {
        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndExpiryAtAfter(tokenValue , new Date());
        if(optionalToken.isEmpty()){
            throw new InvalidTokenException("Invalid Token");
        }
        return optionalToken.get().getUser();
    }
}
