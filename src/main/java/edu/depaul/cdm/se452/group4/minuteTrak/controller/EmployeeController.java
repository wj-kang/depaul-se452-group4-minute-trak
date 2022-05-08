package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/auth")
public class EmployeeController {

  private EmployeeService employeeService;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody EmployeeDTO employeeDTO) {
    EmployeeEntity employee = employeeService.getByCredentials(employeeDTO.getEmail(),
        employeeDTO.getPassword(), passwordEncoder);

    if (employee == null) {
      ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error("Login Failed").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }

    if (employee.isApproved()) {
      /* TODO - Create a new token with eId & role */
      // temporary fake token => payload: {"eId": "1", "role": "employee"} /// secret => "secretkey"
      String token =
          "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJtaW51dGV0cmFrIiwiZUlkIjoiMSIsInJvbGUiOiJlbXBsb3llZSJ9.7Rz6wO232sguCBHB-GlihKludrqja2OvXRXhPADOa6br3lWEw57PV3HyRsp3I03nZomWcFGdmnAYXD6eZfdGuw";

      EmployeeDTO responseEmployeeDTO = EmployeeDTO.builder().email(employee.getEmail())
          .firstName(employee.getFirstName()).lastName(employee.getLastName()).token(token).build();
      return ResponseEntity.ok().body(responseEmployeeDTO);
    }

    // rejected case
    if (employee.isRejected()) {
      ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("Your sign-up request has been rejected").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }

    // pending case
    ResponseDTO<String> responseDTO =
        ResponseDTO.<String>builder().error("Your sign-up request is pending review").build();
    return ResponseEntity.badRequest().body(responseDTO);

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody EmployeeDTO employeeDTO) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
      EmployeeEntity employee = EmployeeEntity.builder()//
          .email(employeeDTO.getEmail())//
          .password(passwordEncoder.encode(employeeDTO.getPassword()))//
          .firstName(employeeDTO.getFirstName())//
          .lastName(employeeDTO.getLastName())//
          .dob(LocalDate.parse(employeeDTO.getDob(), formatter))//
          .phone(employeeDTO.getPhone())//
          .address(employeeDTO.getAddress())//
          .isApproved(false)//
          .isRejected(false)//
          .build();

      EmployeeEntity registeredEmployee = employeeService.create(employee);
      log.info("\n -> New employee registered. Email: {}", registeredEmployee.getEmail());

      EmployeeDTO responseEmployeeDTO = EmployeeDTO.builder()//
          .email(registeredEmployee.getEmail())//
          .build();
      return ResponseEntity.ok().body(responseEmployeeDTO);

    } catch (Exception e) {
      ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error(e.getMessage()).build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
  }

}
