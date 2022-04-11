package edu.depaul.cdm.se452.group4.minuteTrak.persistence;

import org.springframework.stereotype.Repository;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

  EmployeeEntity findByEmail(String email);

  Boolean existsByEmail(String email);

}
