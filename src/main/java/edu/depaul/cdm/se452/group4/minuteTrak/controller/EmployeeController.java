package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.EmployeeDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping("/signup") // needs to be updated with user password and more info
  public ResponseEntity<?> registerUser(@RequestBody EmployeeDTO employeeDTO) {
    try {
      EmployeeEntity employee = EmployeeEntity.builder()//
          .email(employeeDTO.getEmail())//
          .firstName(employeeDTO.getFirstName())//
          .lastName(employeeDTO.getLastName())//
          .build();

      EmployeeEntity registeredEmployee = employeeService.create(employee);
      log.info("\n ->  New user registered. Email: {}", registeredEmployee.getEmail());

      EmployeeDTO responseEmployeeDTO = EmployeeDTO.builder()//
          .email(registeredEmployee.getEmail())//
          .firstName(registeredEmployee.getFirstName())//
          .lastName(registeredEmployee.getLastName())//
          .build();

      return ResponseEntity.ok().body(responseEmployeeDTO);

    } catch (Exception e) {
      ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error(e.getMessage()).build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
  }

}
