package edu.depaul.cdm.se452.group4.minuteTrak.persistence.mongo;

import edu.depaul.cdm.se452.group4.minuteTrak.model.mongo.MongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTestRepository extends MongoRepository<MongoTest, String> {

}
