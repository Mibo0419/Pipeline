package com.quackcoders.Pipeline.Service;

import com.quackcoders.Pipeline.Controllers.RequestController;
import com.quackcoders.Pipeline.Model.Request;
import com.quackcoders.Pipeline.Repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RequestService {
    private static final Logger log = LoggerFactory.getLogger(RequestService.class);
    @Autowired
    private RequestRepository requestRepository;

    public Request generateRequest(Map<String,String> request) {
        log.info(request.toString());
        Request newest = new Request(request.get("type"));

        return requestRepository.insert(newest);
    }
}
