package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group4.minuteTrak.dto.TimesheetDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.WorksDTO;

@RestController 
@RequestMapping("/timesheet")

public class TimesheetController {
    
  @GetMapping
  public ResponseEntity<?> testController() {

    List<WorksDTO> workList = new ArrayList<WorksDTO>();
    for (int i = 0; i < 5; i++) {
      WorksDTO work = WorksDTO.builder()//
          .pId(i)//
          .projectName("project " + i).build();

      work.getHours().put("2022-05-01", 7);
      work.getHours().put("2022-05-02", 8);
      work.getHours().put("2022-05-03", 8);

      workList.add(work);
    }

    TimesheetDTO timesheetDTO = TimesheetDTO.builder()//
        .tID(1)//
        .startDate("2022-04-01")//
        .endDate("2022-04-14")//
        .isSubmitted(true)//
        .isApproved(false).isRejected(false)//
        .works(workList)//
        .build();

    timesheetDTO.getPto().put("2022-05-04", 8);
    timesheetDTO.getPto().put("2022-05-05", 8);
    timesheetDTO.getPto().put("2022-05-06", 8);

    return ResponseEntity.ok().body(timesheetDTO);

  }
}




