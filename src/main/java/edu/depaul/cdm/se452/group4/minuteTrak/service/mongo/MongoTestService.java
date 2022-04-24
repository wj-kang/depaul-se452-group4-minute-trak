package edu.depaul.cdm.se452.group4.minuteTrak.service.mongo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.depaul.cdm.se452.group4.minuteTrak.model.mongo.MongoTest;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.mongo.MongoTestRepository;

@Service
public class MongoTestService {

  private final MongoTestRepository mongoTestRepository;

  @Autowired
  public MongoTestService(MongoTestRepository mongoTestRepository) {
    this.mongoTestRepository = mongoTestRepository;
  }

  public void api1service(MongoTest mt) {
    mongoTestRepository.insert(mt);
  }

  public List<MongoTest> api2service() {
    return mongoTestRepository.findAll();
  }
}
