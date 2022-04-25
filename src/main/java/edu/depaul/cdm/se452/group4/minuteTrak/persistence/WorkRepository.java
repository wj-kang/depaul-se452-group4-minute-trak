package edu.depaul.cdm.se452.group4.minuteTrak.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.model.WorkEntity;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Long> {

}
