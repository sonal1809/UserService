package org.example.userservice25.repositories;

import org.example.userservice25.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {


    Token save(Token token);

    Optional<Token>findByTokenValue(String tokenValue);

    // Validate the token
    Optional<Token> findByTokenValueAndExpiryAtAfter(String tokenValue, Date expiryAt);
}
