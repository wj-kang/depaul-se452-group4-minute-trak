package edu.depaul.cdm.se452.group4.minuteTrak.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.model.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

  Boolean existsByName(String name);

}
