package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group4.minuteTrak.dto.TimeOffRequestDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimeOffRequestRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.security.AuthStatus;
import edu.depaul.cdm.se452.group4.minuteTrak.service.EmployeeService;
import edu.depaul.cdm.se452.group4.minuteTrak.service.TimeOffRequestService;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.crypto.dsig.TransformService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigTreeConfigDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TimeOffRequestController {

    // @Autowired
    // private TimeOffRequestRepository timeOffRequestRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TimeOffRequestService timeOffRequestService;

    public TimeOffRequestController(TimeOffRequestService timeOffRequestService) {
        this.timeOffRequestService = timeOffRequestService;
    }

    @PostMapping("/timeoff")
    public ResponseEntity<?> create(@AuthenticationPrincipal AuthStatus authStatus,
            @RequestBody TimeOffRequestDTO newTimeOffRequestDTO) {

        if (authStatus.getRole().equals("employee")) {
            long eId = authStatus.getId();
            new EmployeeEntity();
            // EmployeeEntity employee = employeeService.findById(eId);
            EmployeeEntity employee = EmployeeEntity.builder()
                .firstName(
                    "John")
                    .build();


            TimeOffRequestDTO dto = newTimeOffRequestDTO;
            TimeOffRequestEntity newTimeOffRequestEntity = TimeOffRequestEntity.builder()
                .employee(employee) // employee from authStatus
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .isPaid(dto.isPaid())
                .build();
            
            TimeOffRequestEntity createdTimeOffRequestEntity  = timeOffRequestService.create(newTimeOffRequestEntity);

            TimeOffRequestDTO createdTimeOffRequestDTO = TimeOffRequestDTO.builder()
                .reqId(createdTimeOffRequestEntity.getReqId())
                .build();
            
                return ResponseEntity.status(201).body(createdTimeOffRequestDTO);

        } else {
            return ResponseEntity.status(403).body("is not employee");
        }

    }

    @GetMapping("/timeoff/{request_id}")
    public ResponseEntity<?> findById(@AuthenticationPrincipal AuthStatus authStatus,
            @PathVariable("request_id") Long reqId) {

        if (authStatus.getRole().equals("employee")) {
            Optional<TimeOffRequestEntity> o = timeOffRequestService.findById(reqId);
            if (o.isPresent()) {
                TimeOffRequestEntity e = o.get();
                TimeOffRequestDTO timeOffRequestDTO =  TimeOffRequestDTO.builder()
                .reqId(e.getReqId())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .reason(e.getReason())
                .isPaid(e.isPaid())
                .isApproved(e.isApproved())
                .isRejected(e.isRejected())
                .build();

                return ResponseEntity.ok().body(timeOffRequestDTO);
            } else {
                return ResponseEntity.status(400).body("cannot find time-off request");
            }

        } else {
            return ResponseEntity.status(403).body("is not employee");
        }
    }

    @GetMapping("/timeoff/list")
    // public List<TimeOffRequestEntity> getTimeOffRequestList()) {
    public ResponseEntity<?> findAll(@AuthenticationPrincipal AuthStatus authStatus) {

        if (authStatus.getRole().equals("employee")) {
            long eId = authStatus.getId();
            List<TimeOffRequestEntity> timeOffRequestEntities = timeOffRequestService.findAll();
            List<TimeOffRequestDTO> timeOffRequestDTOs = new ArrayList<>();
            for (TimeOffRequestEntity e : timeOffRequestEntities) {
                if (e.getEmployee().getEId() == eId) {
                    TimeOffRequestDTO dto = TimeOffRequestDTO.builder()
                            .reqId(e.getReqId())
                            .startDate(e.getStartDate())
                            .endDate(e.getEndDate())
                            .reason(e.getReason())
                            .isPaid(e.isPaid())
                            .isApproved(e.isApproved())
                            .isRejected(e.isRejected())
                            .build();

                    timeOffRequestDTOs.add(dto);
                }
            }
            return ResponseEntity.ok().body(timeOffRequestDTOs);

        } else {
            return ResponseEntity.status(403).body("is not employee");
        }

    }

}
