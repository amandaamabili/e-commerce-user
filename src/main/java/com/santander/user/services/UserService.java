package com.santander.user.services;

import com.santander.user.exceptions.UserEmailDuplicatedException;
import com.santander.user.exceptions.UserNotFoundException;
import com.santander.user.model.User;
import com.santander.user.model.UserDTO;
import com.santander.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).size() > 0) {
            throw new UserEmailDuplicatedException();
        }
        return userRepository.save(new User(dto));
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
    }

    @Override
    public User get(String userID) {
        var user = userRepository.findById(userID);
        return user.orElseThrow(UserNotFoundException::new);
    }
}
