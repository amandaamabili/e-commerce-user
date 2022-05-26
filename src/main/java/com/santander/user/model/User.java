package com.santander.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;

    public User(UserDTO userDTO){
       this.name = userDTO.getName();
       this.email = userDTO.getEmail();
    }
}
