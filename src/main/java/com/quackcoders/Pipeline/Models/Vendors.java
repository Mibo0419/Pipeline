package com.quackcoders.Pipeline.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Vendors")
public class Vendors {

    @Id
    private String id;

    private String name;
    private String type;

    @Field(name = "phone_number")
    private String phoneNumber;

    private String address;
    private String email;

}
