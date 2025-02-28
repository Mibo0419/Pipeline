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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submittedDate) {
        this.submittedDate = submittedDate;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDateTime closedDate) {
        this.closedDate = closedDate;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Request(String type, String description) {
        this();
        this.type = type;
        this.description = description;
    }
    //Lines below will automatically provide date+have status as open
    public Request(){
        this.submittedDate = LocalDateTime.now(); //grabs current time
        this.status = "Open"; //automatically set status for new request

    }

    @Override
    public String toString() {
        return "type : " + type + "\n"+
                "status : " + status + "\n" +
                "submittedDate : " + submittedDate + "\n" +
                "description : " + description;
    }
}
