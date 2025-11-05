package org.example.userservice25.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Token extends BaseModel{
    private String tokenValue;
    private Date expiryAt;
    private User user;

}
