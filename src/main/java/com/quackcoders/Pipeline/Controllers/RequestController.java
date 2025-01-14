package com.quackcoders.Pipeline.Controllers;

import com.quackcoders.Pipeline.Model.Request;

import com.quackcoders.Pipeline.Service.RequestService;
import org.slf4j.Logger;
import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class RequestController {

    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;



    @PostMapping (path = "/request")
    public ResponseEntity generateRequest(@RequestBody Map<String, String> request, Principal principal) {
        log.info("Current user: " + principal.getName());
        log.info("Generating Request");

        Request submittedRequest = new Request();
        try {
            if(!request.isEmpty() && request.containsKey("type")){
                submittedRequest = requestService.generateRequest(request);
            }
        } catch (Exception e) {
            log.info("Exception : {} ", e.getMessage());
        }
        return ResponseEntity.ok(submittedRequest);
    }
}
