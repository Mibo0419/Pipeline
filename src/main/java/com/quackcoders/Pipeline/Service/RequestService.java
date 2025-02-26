package com.quackcoders.Pipeline.Service;

import com.quackcoders.Pipeline.Controllers.RequestController;
import com.quackcoders.Pipeline.Model.Request;
import com.quackcoders.Pipeline.Repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RequestService {
    private static final Logger log = LoggerFactory.getLogger(RequestService.class);
    @Autowired
    private RequestRepository requestRepository;

    public Request generateRequest(Map<String,String> request) {
        log.info(request.toString());
        Request newest = new Request(request.get("type"), request.get("description"));

        return requestRepository.insert(newest);
    }

    public Request updateRequest(Map<String, String> request, String id) {
        // description, type, status

        Optional<Request> currentRequest = (requestRepository.findById(id));
        Request updatedRequest = new Request();
        currentRequest.ifPresent(req ->{
            updatedRequest.setId(req.getId());
            updatedRequest.setSubmittedDate(req.getSubmittedDate());
            updatedRequest.setTenant(req.getTenant());
            updatedRequest.setAssignee(req.getAssignee());
            updatedRequest.setClosedDate(req.getClosedDate());
            updatedRequest.setType(req.getType());
            updatedRequest.setStatus(req.getStatus());
            updatedRequest.setDescription(req.getDescription());
        });
            if(request.containsKey("status")){
                updatedRequest.setStatus(request.get("status"));
            }   if(request.containsKey("type")){
                updatedRequest.setType(request.get("type"));
            }   if(request.containsKey("description")){
                updatedRequest.setDescription(request.get("description"));
            }

            return requestRepository.save(updatedRequest);

    }

    public void deleteRequestById(String id){
        Request deletedRequest = new Request();

        if(requestRepository.existsById(id)){

            requestRepository.deleteById(id);
            System.out.println("Deleted Successfully/Yeeted out of existence");
        }
        else{
            System.out.println("Nothing was deleted");
        }
    }


    public Optional<Request> getRequestById(String id) {
        Optional<Request> getSpecificRequest;
        getSpecificRequest = requestRepository.findById(id);
        return getSpecificRequest;
    }

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }
}
