package com.quackcoders.Pipeline.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class Request {

    @Id
    private String id;

    private String type;
    private String status;

    private LocalDateTime submittedDate;
    private LocalDateTime closedDate;

    private String tenant;
    private String worker;

    private String description;

}
