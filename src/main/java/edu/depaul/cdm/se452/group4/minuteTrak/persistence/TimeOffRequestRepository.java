package edu.depaul.cdm.se452.group4.minuteTrak.persistence;

import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> 6735b08a92dbb32b9dc5c75588184d1b96e45ee6
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;

@Repository
public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequestEntity, Long> {

    public TimeOffRequestEntity save(TimeOffRequestEntity timeOffRequestEntity); 

    public List<TimeOffRequestEntity> findAll();

    public List<TimeOffRequestEntity> findAllByEmployee(EmployeeEntity employee);
}
