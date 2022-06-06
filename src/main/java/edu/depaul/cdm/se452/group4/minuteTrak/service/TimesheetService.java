package edu.depaul.cdm.se452.group4.minuteTrak.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.EmployeeRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimesheetRepository;

@Service
public class TimesheetService {
  @Autowired
  private TimesheetRepository timesheetRepository;
  @Autowired
  private EmployeeRepository employeeRepository;

  public TimesheetEntity create(long eId, TimesheetEntity timesheetEntity) {
    if (timesheetEntity == null || timesheetEntity.getStartDate() == null
        || timesheetEntity.getEndDate() == null) {
      throw new RuntimeException("Invalid entity"); // TODO - exception handling
    }
    timesheetEntity.setEmployee(employeeRepository.getById(eId));
    return timesheetRepository.save(timesheetEntity);
  }

  public TimesheetEntity saveEntity(TimesheetEntity timesheetEntity) {
    return timesheetRepository.save(timesheetEntity);
  }

  public List<TimesheetEntity> getTimesheetEntitiesById(long eId) {
    return timesheetRepository.findAllByEmployee(employeeRepository.getById(eId));

  }

  public TimesheetEntity getTimesheetEntityByEIdAndTId(long eId, long tId) {
    TimesheetEntity entity = timesheetRepository.getById(tId);
    if (entity == null || entity.getEmployee().getEId() != eId) {
      // If there is no entity with the given tId,
      // or if eId is not matched with the found entity, return null
      return null;
    } ;
    return entity;
  }
}
