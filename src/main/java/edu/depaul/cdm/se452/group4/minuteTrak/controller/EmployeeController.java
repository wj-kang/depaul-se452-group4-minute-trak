package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.EmployeeDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.security.TokenProvider;
import edu.depaul.cdm.se452.group4.minuteTrak.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class EmployeeController {

  private EmployeeService employeeService;

  private TokenProvider tokenProvider;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  public EmployeeController(EmployeeService employeeService, TokenProvider tokenProvider) {
    this.employeeService = employeeService;
    this.tokenProvider = tokenProvider;
  }

  @GetMapping("/guest")
  public ResponseEntity<?> guestUser() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
      EmployeeEntity guest = EmployeeEntity.builder()//
          .email(UUID.randomUUID().toString().substring(0, 12) + "@minutetrak.guest")//
          .password(UUID.randomUUID().toString())//
          .firstName("John")//
          .lastName("Doe")//
          .dob(LocalDate.parse("2000-01-01", formatter))//
          .phone("312-123-1234")//
          .address("1 E Jackson Blvd")//
          .isApproved(true)//
          .isRejected(false)//
          .build();

      EmployeeEntity registeredGuest = employeeService.create(guest);
      log.info("\n -> New guest registered. {}", registeredGuest.getEmail());

      /* Create a JWT token including id & role */
      String token = tokenProvider.createEmployeeToken(registeredGuest);

      EmployeeDTO responseEmployeeDTO = EmployeeDTO.builder().email(registeredGuest.getEmail())
          .firstName(registeredGuest.getFirstName()).lastName(registeredGuest.getLastName())
          .token(token).build();
      return ResponseEntity.ok().body(responseEmployeeDTO);

    } catch (Exception e) {
      ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error(e.getMessage()).build();
      return ResponseEntity.badRequest().body(responseDTO);
    }


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
      /* Create a JWT token including id & role */
      String token = tokenProvider.createEmployeeToken(employee);

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
