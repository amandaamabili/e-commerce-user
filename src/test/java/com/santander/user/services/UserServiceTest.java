package com.santander.user.services;

import com.santander.user.exceptions.UserEmailDuplicatedException;
import com.santander.user.model.User;
import com.santander.user.model.UserDTO;
import com.santander.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Test
    void create() {
        User user = new User("2231AS@12", "User", "user@gmail.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        var service = new UserService(userRepository);
        var userCreated = service.save(new UserDTO(Optional.empty(), "User", "user@gmail.com", "084084"));
        Assertions.assertEquals(user.getEmail(), userCreated.getEmail());
        Assertions.assertEquals(user.getName(), userCreated.getName());
    }

    @Test
    void createUserWithEmailAlreadyRegistered() {

        User user = new User("2231AS@12", "User", "user@gmail.com");
        List<User> userList = List.of(user);

        when(userRepository.findByEmail(any(String.class))).
                thenAnswer(invocation -> {
                    String email = invocation.getArgument(0);
                    return userList.stream()
                            .filter(user1 -> Objects.equals(user1.getEmail(), email)).collect(Collectors.toList());
                });

        var service = new UserService(userRepository);
        Assertions.assertThrows(
                UserEmailDuplicatedException.class,
                () -> service.save(new UserDTO(Optional.empty(), "User", "user@gmail.com", "084084")));
    }

    @Test
    void update() {
        User previousUser = new User("2231AS@12", "User", "user@gmail.com");
        UserDTO userDTO = new UserDTO(Optional.of("2231AS@12"), "NewUser", "newUser@gmail.com", "0894084");
        User currentUser = new User("2231AS@12", "NewUser", "newUser@gmail.com");
        var captor = ArgumentCaptor.forClass(User.class);

        when(userRepository.findById("2231AS@12")).thenReturn(Optional.of(previousUser));
        when(userRepository.save(any(User.class))).thenReturn(currentUser);

        var service = new UserService(userRepository);
        User update = service.update(previousUser.get_id(), userDTO);

        Assertions.assertNotNull(update);
        verify(userRepository).save(captor.capture());

        Assertions.assertEquals(currentUser.getName(), captor.getValue().getName());
        Assertions.assertEquals(currentUser.getEmail(), captor.getValue().getEmail());
    }

    @Test
    void updateUserWithNameAlreadyRegistered() {
        User previousUser = new User("2231AS@12", "User", "user@gmail.com");
        UserDTO userDTO = new UserDTO(Optional.of("2231AS@12"), "NewUser", "user@gmail.com", "0894084");

        List<User> userList = List.of(previousUser);

        when(userRepository.findById("2231AS@12")).thenReturn(Optional.of(previousUser));
        when(userRepository.findByEmail(any(String.class))).
                thenAnswer(invocation -> {
                    String email = invocation.getArgument(0);
                    return userList.stream()
                            .filter(user1 -> Objects.equals(user1.getEmail(), email)).collect(Collectors.toList());
                });

        var service = new UserService(userRepository);
        Assertions.assertThrows(UserEmailDuplicatedException.class, () -> service.update(previousUser.get_id(), userDTO));

        verify(userRepository, never()).save(any(User.class));
        verify(userRepository).findById("2231AS@12");
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }
}