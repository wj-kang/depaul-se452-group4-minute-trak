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

public class TimesheetDTO {

    private long tId;

    private String startDate;

    private String endDate;

    private boolean isSubmitted;

    private boolean isApproved;

    private boolean isRejected;

    private List<WorksDTO> works;

    @Builder.Default
    private Map<String, Integer> pto = new HashMap<>();

}
