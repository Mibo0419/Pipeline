package com.quackcoders.Pipeline.Controllers;

import com.quackcoders.Pipeline.Model.Request;

import com.quackcoders.Pipeline.Service.RequestService;
import org.slf4j.Logger;
import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class RequestController {

    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;



    @PostMapping (path = "/newRequest")
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

    @PutMapping(path = "/updateRequest/{id}")
    public ResponseEntity updateRequest(@RequestBody Map<String, String> request, Principal principal,
                                        @PathVariable String id){
        Request updatedRequest = new Request();
        try{
           updatedRequest = requestService.updateRequest(request, id);
        }catch(Exception e){
            log.info("Exception : update failed" , e.getMessage());
        }
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping(path = "/deletion/{id}")
    public ResponseEntity deleteRequest(@PathVariable String id){
        requestService.deleteRequestById(id);
        return ResponseEntity.ok("Request deleted");
    }

    @GetMapping(path="/requests")
    public ResponseEntity getAllRequests(){
        List<Request> allRequests = requestService.getAllRequests();
        return  ResponseEntity.ok(allRequests);
    }
    @GetMapping(path="/requests/{id}")
    public ResponseEntity getSpecificRequestById(@PathVariable String id){
        Request request = requestService.getRequestById(id).orElseThrow();

        return ResponseEntity.ok().body(request);
    }
}
