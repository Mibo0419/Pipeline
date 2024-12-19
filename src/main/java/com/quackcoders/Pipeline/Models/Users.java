package com.quackcoders.Pipeline.Models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class Users {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Roles roles;

}
