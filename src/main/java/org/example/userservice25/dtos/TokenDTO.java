package org.example.userservice25.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice25.models.Token;

import java.util.Date;

@Getter
@Setter
public class TokenDTO {
    private String tokenValue;
    private String email;
    private Date ExpiryAt;


    public static TokenDTO from(Token token){
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setTokenValue(token.getTokenValue());
        tokenDTO.setExpiryAt(token.getExpiryAt());
        tokenDTO.setEmail(token.getUser().getEmail());
        return tokenDTO;
    }
}
