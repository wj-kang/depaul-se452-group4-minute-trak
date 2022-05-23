package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import java.time.LocalDate;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private boolean isSubmitted;

    private boolean isApproved;

    private boolean isRejected;

    private List<WorksDTO> works;

    @Builder.Default
    private Map<String, Integer> pto = new HashMap<>();
}
