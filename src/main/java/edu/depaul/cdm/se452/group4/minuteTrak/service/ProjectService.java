package edu.depaul.cdm.se452.group4.minuteTrak.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.depaul.cdm.se452.group4.minuteTrak.model.ProjectEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.ProjectRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectService {
  private ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public ProjectEntity create(ProjectEntity projectEntity) {
    if (projectEntity == null || projectEntity.getName() == null) {
      throw new RuntimeException("Invalid entity");
    }

    String projectName = projectEntity.getName();
    if (projectRepository.existsByName(projectName)) {
      log.warn("Duplicated Project Name {}", projectName);
      throw new RuntimeException("already exists");
    }

    return projectRepository.save(projectEntity);
  }

  public List<ProjectEntity> getAllProjectEntities() {
    return projectRepository.findAll();
  }
}
