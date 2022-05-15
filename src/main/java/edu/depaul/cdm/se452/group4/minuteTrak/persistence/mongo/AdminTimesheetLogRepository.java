package edu.depaul.cdm.se452.group4.minuteTrak.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.model.mongo.AdminTimesheetLog;

public interface AdminTimesheetLogRepository extends MongoRepository<AdminTimesheetLog, String> {

}
