package com.quackcoders.Pipeline.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class Users {
    @id
    private String id;
    private String name;
    private String email;
    private String password;
    private Roles roles;

}
