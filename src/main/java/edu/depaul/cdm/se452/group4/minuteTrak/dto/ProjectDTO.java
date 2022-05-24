package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

  private long pId;

  private String name;

  private int budget;

}
