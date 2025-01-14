package com.quackcoders.Pipeline.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Principal;
import java.time.LocalDateTime;
@Data
@Document(collection = "Maintenance_requests")
public class Request {

    private static final Logger logger = LoggerFactory.getLogger(Request.class);

    @Id
    private String id;

    private String type;
    private String status;

    private LocalDateTime submittedDate;
    private LocalDateTime closedDate;

    private String tenant; //Owner of unit/apartment

    private String assignee; //Made initial request

    private String description;

    public Request(String type) {
        this();
        this.type = type;
    }

    public Request(){
        this.submittedDate = LocalDateTime.now(); //grabs current time
        this.status = "In-Progress"; //automatically set status for new request
    }

    @Override
    public String toString() {
        return "type : " + type + "\n"+
                "status : " + status + "\n" +
                "submittedDate : " + submittedDate + "\n" +
                "description : " + description;
    }
}
