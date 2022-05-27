package com.santander.user.services;

import com.santander.user.model.User;
import com.santander.user.model.UserDTO;

public interface UserServiceInterface {

    User save(UserDTO dto);
    User update(String userID, UserDTO userDTO);
    void delete(String userID);
    User get(String userID);

}
