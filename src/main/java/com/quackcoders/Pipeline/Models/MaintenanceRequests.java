package com.quackcoders.Pipeline.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "Maintenance_requests")
public class MaintenanceRequests {

    @Id
    private String id;

    private String description;

    @Field(name = "request_type")
    private String requestType;

    @Field(name = "date_submitted")
    private LocalDateTime dateSubmitted;

    @Field(name = "date_closed")
    private LocalDateTime dateClosed;


    private String status;

    @DBRef
    private Users users;
}
