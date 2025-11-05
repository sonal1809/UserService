package org.example.userservice25.dtos;


import lombok.Getter;
import lombok.Setter;
import org.example.userservice25.models.Role;
import org.example.userservice25.models.User;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String email;
    private List<Role> roles;


    public static UserDTO fromUser(User user){
        if(user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUserId(user.getId());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
