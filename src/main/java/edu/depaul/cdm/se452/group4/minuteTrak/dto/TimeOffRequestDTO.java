package edu.depaul.cdm.se452.group4.minuteTrak.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeOffRequestDTO {

    private long reqId;

    @JsonFormat(pattern = "yyyy-MM-dd")

    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime toDate;

    private boolean isPaid;

    private boolean isApproved;

}
