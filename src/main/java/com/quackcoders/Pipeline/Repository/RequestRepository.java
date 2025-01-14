package com.quackcoders.Pipeline.Repository;

import com.quackcoders.Pipeline.Model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository <Request ,String> {


}
