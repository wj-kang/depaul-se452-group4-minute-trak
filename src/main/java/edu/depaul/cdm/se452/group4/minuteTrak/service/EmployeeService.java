package edu.depaul.cdm.se452.group4.minuteTrak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public EmployeeEntity create(EmployeeEntity employeeEntity) {
    if (employeeEntity == null || employeeEntity.getEmail() == null) {
      throw new RuntimeException("Invalid entity");
    }

    String email = employeeEntity.getEmail();
    if (employeeRepository.existsByEmail(email)) {
      log.warn("Email already exists {}", email);
      throw new RuntimeException("Email already exists");
    }

    return employeeRepository.save(employeeEntity);
  }

  public EmployeeEntity getByCredentials(String email, String password, PasswordEncoder encoder) {
    EmployeeEntity originalUser = employeeRepository.findByEmail(email);

    if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
      return originalUser;
    }
    return null;
  }

}
