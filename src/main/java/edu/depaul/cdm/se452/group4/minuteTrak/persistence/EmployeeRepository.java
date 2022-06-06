package edu.depaul.cdm.se452.group4.minuteTrak.persistence;

import org.springframework.stereotype.Repository;
import java.util.List;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

  EmployeeEntity findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean deleteById(long employeeId); 

  EmployeeEntity save(EmployeeEntity employeeEntity);

  List<EmployeeEntity> findAll(); 


  

}
