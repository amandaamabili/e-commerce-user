package com.santander.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class UserDTO {

    private Optional<String> id;
    private String name;
    private String email;
    private String cpf;

}
