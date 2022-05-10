package edu.depaul.cdm.se452.group4.minuteTrak.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;

@Repository
public interface TimesheetRepository extends JpaRepository<TimesheetEntity, Long> {

}
