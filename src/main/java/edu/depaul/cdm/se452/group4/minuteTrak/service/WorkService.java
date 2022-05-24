package edu.depaul.cdm.se452.group4.minuteTrak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.depaul.cdm.se452.group4.minuteTrak.model.WorkEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.ProjectRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimesheetRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.WorkRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WorkService {

  private WorkRepository workRepository;
  private TimesheetRepository timesheetRepository;
  private ProjectRepository projectRepository;

  @Autowired
  public WorkService(WorkRepository workRepository, TimesheetRepository timesheetRepository,
      ProjectRepository projectRepository) {
    this.workRepository = workRepository;
    this.timesheetRepository = timesheetRepository;
    this.projectRepository = projectRepository;
  }

  public WorkEntity create(long tId, long pId, WorkEntity workEntity) {
    if (workEntity == null || workEntity.getDate() == null) {
      throw new RuntimeException("Invalid entity");
    }

    workEntity.setTimesheet(timesheetRepository.getById(tId));
    workEntity.setProject(projectRepository.getById(pId));

    return workRepository.save(workEntity);
  }

}
