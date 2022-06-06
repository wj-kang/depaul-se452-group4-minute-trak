package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.depaul.cdm.se452.group4.minuteTrak.model.SalaryEntity;
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

  private boolean isApproved; 

  private boolean isRejected; 

  private Date createdTime; 

  private int ptoBank; 

  private SalaryEntity salary; 
  
  private String address;

  private String token;

}
