package com.quackcoders.Pipeline.Repository;

import com.quackcoders.Pipeline.Model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends MongoRepository <Request ,String> {


        List<Request> findAll();

    void deleteById(String id);

//      Request findById();
}
