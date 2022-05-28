package edu.depaul.cdm.se452.group4.minuteTrak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.EmployeeRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimeOffRequestRepository;


@Service
public class TimeOffRequestService {

    @Autowired
    private TimeOffRequestRepository timeOffRequestRepository;
    private EmployeeRepository employeeRepository;

    public TimeOffRequestEntity create(long eId, TimeOffRequestEntity timeOffRequestEntity) {
        if (timeOffRequestEntity == null || timeOffRequestEntity.getFromDate() == null
            || timeOffRequestEntity.getToDate() == null) {
          throw new RuntimeException("Invalid entity"); // TODO - exception handling
        }
        timeOffRequestEntity.setEmployee(employeeRepository.getById(eId));
        return timeOffRequestRepository.save(timeOffRequestEntity);
      }

    public TimeOffRequestEntity saveEntity(TimeOffRequestEntity timeOffRequestEntity) {
        return timeOffRequestRepository.save(timeOffRequestEntity);
      }

    public List<TimeOffRequestEntity> getTimeOffRequestEntitiesById(long eId) {
        return timeOffRequestRepository.findAllByEmployee(employeeRepository.getById(eId));
      }

      public TimeOffRequestEntity getTimesheetEntityByEIdAndReqId(long eId, long reqId) {
        TimeOffRequestEntity entity = timeOffRequestRepository.getById(reqId);
        if (entity == null || entity.getEmployee().getEId() != eId) {
          return null;
        } ;
        return entity;
      }


}
