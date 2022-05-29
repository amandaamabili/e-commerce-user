package com.santander.user.services;

import com.santander.user.client.SaleClientAPI;
import com.santander.user.exceptions.UserEmailDuplicatedException;
import com.santander.user.exceptions.UserNotFoundException;
import com.santander.user.model.User;
import com.santander.user.model.UserDTO;
import com.santander.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final SaleClientAPI saleAPIClient;

    public UserService(UserRepository userRepository, SaleClientAPI saleAPIClient) {

        this.userRepository = userRepository;
        this.saleAPIClient = saleAPIClient;
    }

    @Override
    public User save(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).size() > 0) {
            throw new UserEmailDuplicatedException();
        }
        var user = userRepository.save(new User(dto));
        saleAPIClient.createCart(user.get_id());
        return user;
    }

    @Override
    public User update(String userID, UserDTO dto) {
        if (userRepository.findById(userID).isEmpty()) {
            throw new UserNotFoundException();
        }
        dto.setId(Optional.of(userID));
        return this.save(dto);
    }

    @Override
    public void delete(String userID) {
        Optional<User> user = userRepository.findById(userID);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.delete(user.get());
        saleAPIClient.deleteCart(userID);
    }

    @Override
    public User get(String userID) {
        var user = userRepository.findById(userID);
        return user.orElseThrow(UserNotFoundException::new);
    }
}
