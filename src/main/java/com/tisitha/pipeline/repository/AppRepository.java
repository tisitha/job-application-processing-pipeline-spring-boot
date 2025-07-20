package com.tisitha.pipeline.repository;

import com.tisitha.pipeline.model.ApplicantData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends MongoRepository<ApplicantData, String> {
}
