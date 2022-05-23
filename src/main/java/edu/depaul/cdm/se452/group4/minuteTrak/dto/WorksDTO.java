package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WorksDTO {

    private int pId;

    private String projectName;
  
    @Builder.Default
    private Map<String, Integer> hours = new HashMap<>();
}
