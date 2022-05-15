package edu.depaul.cdm.se452.group4.minuteTrak.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.model.mongo.Timesheets;

public interface TimesheetsRepository extends MongoRepository<Timesheets, String> {

}
