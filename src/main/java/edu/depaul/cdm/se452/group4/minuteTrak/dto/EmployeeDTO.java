package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
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

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dob;

  private String address;

  private String token;

}
