package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

  private long adminId;

  private String email;

  private String password;

  private String account; 
  
  private String token;

}
