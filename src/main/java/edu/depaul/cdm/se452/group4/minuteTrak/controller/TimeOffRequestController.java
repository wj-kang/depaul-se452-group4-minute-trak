package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.TimeOffRequestDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;

import edu.depaul.cdm.se452.group4.minuteTrak.security.AuthStatus;
import edu.depaul.cdm.se452.group4.minuteTrak.service.TimeOffRequestService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequestMapping("/timeoffrequest")
public class TimeOffRequestController {

  @Autowired
  private TimeOffRequestService timeOffRequestService;

  @PostMapping("/new")
  public ResponseEntity<?> newTimeOffRequest(@AuthenticationPrincipal AuthStatus authStatus,
      @RequestBody TimeOffRequestDTO reqBody) {
    if (!authStatus.getRole().equals("employee")) {
      ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("Auth status invalid").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
    long eId = authStatus.getId();

    TimeOffRequestEntity timeOffRequestEntity = TimeOffRequestEntity.builder()//
        .fromDate(reqBody.getFromDate())//
        .toDate(reqBody.getToDate())//
        .reason(reqBody.getReason())//
        .isPaid(reqBody.isPaid())//
        .build();

    TimeOffRequestEntity createdTimeOffRequestEntity =
        timeOffRequestService.create(eId, timeOffRequestEntity);
    log.info("New time off request created. reqId: {}", timeOffRequestEntity.getReqId());

    TimeOffRequestDTO responseDTO = TimeOffRequestDTO.builder()//
        .reqId(createdTimeOffRequestEntity.getReqId())//
        .fromDate(createdTimeOffRequestEntity.getFromDate())//
        .toDate(createdTimeOffRequestEntity.getToDate())//
        .reason(createdTimeOffRequestEntity.getReason())//
        .isPaid(createdTimeOffRequestEntity.isPaid())//
        .build();

    return ResponseEntity.status(201).body(responseDTO);
  }

  @GetMapping("/list")
  public ResponseEntity<?> getTimeOffRequestList(@AuthenticationPrincipal AuthStatus authStatus) {
    if (!authStatus.getRole().equals("employee")) {
      ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("Auth status invalid").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
    long eId = authStatus.getId();

    List<TimeOffRequestEntity> entities = timeOffRequestService.getTimeOffRequestEntitiesById(eId);
    List<TimeOffRequestDTO> timeOffRequestDTOList = new ArrayList<>();

    for (TimeOffRequestEntity entity : entities) {
      timeOffRequestDTOList.add(TimeOffRequestDTO.builder()//
          .reqId(entity.getReqId())//
          .fromDate(entity.getFromDate())//
          .toDate(entity.getToDate())//
          .isPaid(entity.isPaid())//
          .isApproved(entity.isApproved())//
          .isRejected(entity.isRejected())//
          .reason(entity.getReason())//
          .build());
    }
    log.info("TimeOffRequest list data for eId: {} => {}", eId, timeOffRequestDTOList.toString());

    return ResponseEntity.ok().body(timeOffRequestDTOList);
  }

  @GetMapping("/{reqId}")
  public ResponseEntity<?> getTimeOffRequestByReqId(@AuthenticationPrincipal AuthStatus authStatus,
      @PathVariable long reqId) {
    if (!authStatus.getRole().equals("employee")) {
      ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("Auth status invalid").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
    long eId = authStatus.getId();

    TimeOffRequestEntity entity = timeOffRequestService.getTimesheetEntityByEIdAndReqId(eId, reqId);
    TimeOffRequestDTO responseDTO = TimeOffRequestDTO.builder()//
        .reqId(entity.getReqId())//
        .fromDate(entity.getFromDate())//
        .toDate(entity.getToDate())//
        .isPaid(entity.isPaid())//
        .isApproved(entity.isApproved())//
        .isRejected(entity.isRejected())//
        .reason(entity.getReason())//
        .build();

    log.info("Time off request detail data for reqId: {} => {}", reqId, responseDTO.toString());

    return ResponseEntity.ok().body(responseDTO);
  }
}

// @GetMapping("/timeoff/{request_id}")
// public ResponseEntity<?> findById(@PathVariable("request_id") Long reqId) {
// Optional<TimeOffRequestEntity> timeOffRequestEntityOpt = timeOffRequestService.findById(reqId);

// if (timeOffRequestEntityOpt.isPresent()) {
// return ResponseEntity.ok().body(timeOffRequestEntityOpt.get());
// } else {
// return ResponseEntity.badRequest().build();
// }
// }

// @GetMapping("/timeoff/list")
// // public List<TimeOffRequestEntity> getTimeOffRequestList()) {
// public ResponseEntity<?> findAll(@AuthenticationPrincipal AuthStatus authStatus) {

        // final String authRole = authStatus.getRole();
        // List<TimeOffRequestEntity> timeOffRequestEntities;


        // switch (authRole) {
        //     case "admin":
        //         timeOffRequestEntities = timeOffRequestService.findAll();
        //         return ResponseEntity.ok().body(timeOffRequestEntities);

        //     case "employee":
        //        timeOffRequestEntities = timeOffRequestService.findAll();
        //         return ResponseEntity.ok().body(timeOffRequestEntities);

// default:
// return ResponseEntity.badRequest().body("err");
// }

// // if (authRole != "employee") {

// // }
// // final int authId = authStatus.getId();
// // List<TimeOffRequestEntity> timeOffRequestEntities =
// // timeOffRequestRepository.getListById(authId);

// }

// }
