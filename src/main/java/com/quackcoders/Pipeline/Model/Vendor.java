package com.quackcoders.Pipeline.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Vendor {

    @Id
    private String id;

    private String companyName;

    private String phoneNumber;
    private String email;
    private String address;
}
