package edu.depaul.cdm.se452.group4.minuteTrak.dto;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFormat;

=======
>>>>>>> 6735b08a92dbb32b9dc5c75588184d1b96e45ee6
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
