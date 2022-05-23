package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

  private String email;

  private long id;

  private String password;

  private String firstName;

  private String lastName;

  private String phone;

  private String dob;

  private String address;

  private String token;

}
