// package edu.depaul.cdm.se452.group4.minuteTrak.controller;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
// import edu.depaul.cdm.se452.group4.minuteTrak.dto.TimesheetDTO;
// import edu.depaul.cdm.se452.group4.minuteTrak.dto.WorksDTO;
// import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;
// import edu.depaul.cdm.se452.group4.minuteTrak.model.WorkEntity;
// import edu.depaul.cdm.se452.group4.minuteTrak.security.AuthStatus;
// import edu.depaul.cdm.se452.group4.minuteTrak.service.TimesheetService;
// import edu.depaul.cdm.se452.group4.minuteTrak.service.WorkService;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @RestController
// @RequestMapping("/timesheet")
// public class TimesheetController {

//   TimesheetService timesheetService;
//   WorkService workService;
//   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//   @Autowired
//   public TimesheetController(TimesheetService timesheetService, WorkService workService) {
//     this.timesheetService = timesheetService;
//     this.workService = workService;
//   }

//   @PostMapping("/new")
//   public ResponseEntity<?> newTimesheet(@AuthenticationPrincipal AuthStatus authStatus,
//       @RequestBody TimesheetDTO reqBody) {
//     if (!authStatus.getRole().equals("employee")) {
//       ResponseDTO<String> responseDTO =
//           ResponseDTO.<String>builder().error("Auth status invalid").build();
//       return ResponseEntity.badRequest().body(responseDTO);
//     }
//     long eId = authStatus.getId();

//     TimesheetEntity timesheetEntity = TimesheetEntity.builder()//
//         .startDate(reqBody.getStartDate())//
//         .endDate(reqBody.getEndDate())//
//         .build();
//     TimesheetEntity createdTimesheetEntity = timesheetService.create(eId, timesheetEntity);
//     log.info("New timesheet created. tId: {}", createdTimesheetEntity.getTId());

//     TimesheetDTO responseDTO = TimesheetDTO.builder()//
//         .tId(createdTimesheetEntity.getTId())//
//         .startDate(createdTimesheetEntity.getStartDate())//
//         .endDate(createdTimesheetEntity.getEndDate())//
//         .build();

//     return ResponseEntity.status(201).body(responseDTO);
//   }

//   @GetMapping("list")
//   public ResponseEntity<?> getTimesheetList(@AuthenticationPrincipal AuthStatus authStatus) {
//     if (!authStatus.getRole().equals("employee")) {
//       ResponseDTO<String> responseDTO =
//           ResponseDTO.<String>builder().error("Auth status invalid").build();
//       return ResponseEntity.badRequest().body(responseDTO);
//     }
//     long eId = authStatus.getId();

//     List<TimesheetEntity> entities = timesheetService.getTimesheetEntitiesById(eId);
//     List<TimesheetDTO> timesheetDTOList = new ArrayList<>();

//     for (TimesheetEntity entity : entities) {
//       timesheetDTOList.add(TimesheetDTO.builder()//
//           .tId(entity.getTId())//
//           .startDate(entity.getStartDate())//
//           .endDate(entity.getEndDate())//
//           .isSubmitted(entity.isSubmitted())//
//           .isApproved(entity.isApproved())//
//           .isRejected(entity.isRejected())//
//           .build());
//     }
//     log.info("Timesheet list data for eId: {} => {}", eId, timesheetDTOList.toString());

//     return ResponseEntity.ok().body(timesheetDTOList);
//   }

//   @GetMapping("/{tId}")
//   public ResponseEntity<?> getTimesheetList(@AuthenticationPrincipal AuthStatus authStatus,
//       @PathVariable long tId) {
//     if (!authStatus.getRole().equals("employee")) {
//       ResponseDTO<String> responseDTO =
//           ResponseDTO.<String>builder().error("Auth status invalid").build();
//       return ResponseEntity.badRequest().body(responseDTO);
//     }
//     long eId = authStatus.getId();

//     TimesheetEntity entity = timesheetService.getTimesheetEntityByEIdAndTId(eId, tId);
//     TimesheetDTO responseDTO = TimesheetDTO.builder()//
//         .tId(entity.getTId())//
//         .startDate(entity.getStartDate())//
//         .endDate(entity.getEndDate())//
//         .isSubmitted(entity.isSubmitted())//
//         .isApproved(entity.isApproved())//
//         .isRejected(entity.isRejected())//
//         .build();

//     Map<Long, WorksDTO> worksByPId = new HashMap<>();
//     for (WorkEntity w : entity.getWorks()) {
//       long pId = w.getProject().getPId();
//       WorksDTO worksDTO = worksByPId.getOrDefault(pId, WorksDTO.builder()//
//           .pId(pId)//
//           .projectName(w.getProject().getName())//
//           .build());
//       worksDTO.getHours().put(w.getDate().toString(), w.getHours());
//       worksByPId.put(pId, worksDTO);
//     }
//     responseDTO.setWorks(worksByPId.values().stream().toList());
//     log.info("Timesheet detail data for tId: {} => {}", tId, responseDTO.toString());

//     return ResponseEntity.ok().body(responseDTO);
//   }

//   @PostMapping("/submit")
//   public ResponseEntity<?> getTimesheetList(@AuthenticationPrincipal AuthStatus authStatus,
//       @RequestBody TimesheetDTO reqBody) {
//     if (!authStatus.getRole().equals("employee")) {
//       ResponseDTO<String> responseDTO =
//           ResponseDTO.<String>builder().error("Auth status invalid").build();
//       return ResponseEntity.badRequest().body(responseDTO);
//     }
//     long eId = authStatus.getId();
//     long tId = reqBody.getTId();

//     TimesheetEntity timesheetEntity = timesheetService.getTimesheetEntityByEIdAndTId(eId, tId);
//     if (timesheetEntity == null) {
//       ResponseDTO<String> responseDTO =
//           ResponseDTO.<String>builder().error("No record with the given eId & tId").build();
//       return ResponseEntity.badRequest().body(responseDTO);
//     }

//     List<WorksDTO> works = reqBody.getWorks();
//     for (WorksDTO w : works) {
//       long pId = w.getPId();
//       // Iterate through each work hour by date, insert work entity
//       Map<String, Integer> hours = w.getHours();
//       for (Map.Entry<String, Integer> entry : hours.entrySet()) {
//         WorkEntity workEntity = WorkEntity.builder()//
//             .date(LocalDate.parse(entry.getKey(), formatter))//
//             .hours(entry.getValue()).build();
//         workService.create(tId, pId, workEntity);
//       }
//     }

//     timesheetEntity.setSubmitted(true);
//     timesheetService.saveEntity(timesheetEntity);
//     log.info("Submitted timesheet work data => {}", works.toString());

//     return ResponseEntity.ok().body("Submitted");
//   }
// }


