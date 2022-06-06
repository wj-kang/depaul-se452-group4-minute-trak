package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.TimeOffRequestDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.TimesheetDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.AdminDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.EmployeeDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.ApprovedTimeOffEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.security.TokenProvider;
import edu.depaul.cdm.se452.group4.minuteTrak.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

  private AdminService adminService;

  private TokenProvider tokenProvider;
  
  // private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  public AdminController(AdminService adminService, TokenProvider tokenProvider){
    this.adminService = adminService; 
    this.tokenProvider = tokenProvider; 
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody AdminDTO adminDTO) {

    System.out.println(adminDTO.getEmail());
    System.out.println(adminDTO.getPassword());
    AdminEntity admin = adminService.getByCredentials(adminDTO.getEmail(), 
    adminDTO.getPassword());
    
    if(admin == null) {
      System.out.print("INSIDE admin = null in /login");
      ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error("Login Failed").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }


      /* Create a JWT token including id & role */
      String token = tokenProvider.createAdminToken(admin);
      System.out.print("INSIDE post token in /login");

      AdminDTO responseAdminDTO = AdminDTO.builder().account(admin.getAccount())
          .adminId(admin.getAdminId()).token(token).build();
          System.out.print("built admin dto in /login");

      return ResponseEntity.ok().body(responseAdminDTO);

}

@GetMapping(value = "/getAllTimeOffRequests", produces = "application/json")
@ResponseBody
public ResponseEntity<?> getAllTimeOffRequests(){
      List<TimeOffRequestEntity> entities = adminService.getAllTimeOffRequests();
    
      List<TimeOffRequestDTO> timeOffRequestDTOList = new ArrayList<>(); 


      try{
        for(TimeOffRequestEntity entity: entities){
          timeOffRequestDTOList.add(TimeOffRequestDTO.builder()
          .reqId(entity.getReqId())
          .fromDate(entity.getFromDate())
          .toDate(entity.getToDate())
          .isPaid(entity.isPaid())
          .isApproved(entity.isApproved())
          .isRejected(entity.isRejected())
          .employee(EmployeeDTO.builder()
          .firstName(entity.getEmployee().getFirstName())
          .lastName(entity.getEmployee().getLastName())
          .build())
          .build());



        }

        return ResponseEntity.ok().body(timeOffRequestDTOList); 
      

      }
      catch(Exception ex){
        ex.printStackTrace();

      }

    return null; 
}


@GetMapping(value = "/getAllTimeSheets", produces = "application/json")
@ResponseBody
public ResponseEntity<?> getAllTimeSheets(){
      List<TimesheetEntity> entities = adminService.getAllTimeSheets(); 
      List<TimesheetDTO> timesheetDTOList = new ArrayList<>(); 

      try{
        for(TimesheetEntity entity : entities){
        timesheetDTOList.add(TimesheetDTO.builder()
          .tId(entity.getTId())
          .startDate(entity.getStartDate())
          .endDate(entity.getEndDate())
          .isSubmitted(entity.isSubmitted())
          .isApproved(entity.isApproved())
          .isRejected(entity.isRejected())
          .employee(EmployeeDTO.builder()
          .firstName(entity.getEmployee().getFirstName())
          .lastName(entity.getEmployee().getLastName())
          .build())
          .build());
        }
        
        return ResponseEntity.ok().body(timesheetDTOList); 


      }
      catch(Exception ex){
        System.out.println("INSIDE CATCH OF GETALLTIMESHEETS");
        ex.printStackTrace();

      }

      return null; 

}

@GetMapping(value = "/getAllEmployees", produces = "application/json")
@ResponseBody
public ResponseEntity<?> getAllEmployees(){
      List<EmployeeEntity> entities = adminService.getAllEmployees();
      List<EmployeeDTO> employeeDTOList = new ArrayList<>(); 

      try{
          for(EmployeeEntity entity : entities) {
            employeeDTOList.add(EmployeeDTO.builder()
              .id(entity.getEId())
              .email(entity.getEmail())
              .firstName(entity.getFirstName())
              .lastName(entity.getLastName())
              .password(entity.getPassword())
              .address(entity.getAddress())
              .phone(entity.getPhone())
              .dob(entity.getDob())
              .createdTime(entity.getCreatedTime())
              .isApproved(entity.isApproved())
              .isRejected(entity.isRejected())
              .ptoBank(entity.getPtoBank())
              .build()
              );
      }

        return ResponseEntity.ok().body(employeeDTOList); 
    }

      catch(Exception ex){
        System.out.println("INSIDE GET ALL EMPLOYEES CATCH");
        ex.printStackTrace();

      }

    return null; 
}

// @GetMapping(value = "/getEmployeeData", produces = "application/json")
// @ResponseBody
// public ResponseEntity<?> getEmployeeData(
//         @RequestParam(value = "employeeSelected") String employeeSelected
// ){
//       EmployeeEntity emp = adminService.getEmployeeData(employeeSelected);;

//       try{

//       }
//       catch(Exception ex){
//         ex.printStackTrace();

//       }

//     return emp; 
// }

// @PostMapping(value = "/createEmployee", produces = "application/json", consumes = "application/json")
// @ResponseBody
// public String createEmployee(@RequestBody EmployeeDTO employeeDTO, HttpServletRequest request) {  
//     try {
//       HttpSession session = request.getSession(false); 
//       String dlUserId = null; 
//       if(session != null){
//         dlUserId = (String) session.getAttribute("eId");
//         employeeDTO.setId(dlUserId);
//         return adminService.createEmployee(employeeDTO);
//       }


//     }
//     catch(Exception exception){
//       exception.printStackTrace();

//     }

//     return null; 
// }

//this one needs to update timeOffReuqestEntity and create ApprovedTimeOffEntity
@PutMapping(value="/updateTimeRequest", produces = "application/json")
@ResponseBody
public ResponseEntity<?> updateTimeRequest(@RequestBody TimeOffRequestDTO reqBody){
  
  long rId = reqBody.getReqId(); 
  TimeOffRequestEntity timeOffRequestEntity = adminService.getTimeOffRequestEntityByRId(rId); 

  if(timeOffRequestEntity == null){
    ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("No record with the given eId & tId").build();
      return ResponseEntity.badRequest().body(responseDTO);
  }
  try {
    if(reqBody.isApproved()){
      List<ApprovedTimeOffEntity> approvedTimeOffEntities = new ArrayList<>(); 
      timeOffRequestEntity.setApproved(true);

      LocalDate start = timeOffRequestEntity.getFromDate(); 
      LocalDate end = timeOffRequestEntity.getToDate(); 

      for(LocalDate date = start; date.isBefore(end); date = date.plusDays(1)){
        ApprovedTimeOffEntity approvedTimeOffEntity = new ApprovedTimeOffEntity(); 
        approvedTimeOffEntity.setDate(date);
        approvedTimeOffEntity.setPaid(timeOffRequestEntity.isPaid());
        approvedTimeOffEntity.setTimeOffRequest(timeOffRequestEntity); 
        approvedTimeOffEntities.add(approvedTimeOffEntity); 
      }
      adminService.addApprovedTimeOffs(approvedTimeOffEntities); 

    }
    else
      timeOffRequestEntity.setRejected(true);

    adminService.updateTimeRequest(timeOffRequestEntity); 

    return ResponseEntity.ok().body("Successfully submitted");
  }
  catch(Exception exception){
    exception.printStackTrace();

  }

  return null; 

}



@PostMapping(value="/updateTimesheet", produces = "application/json")
@ResponseBody
public ResponseEntity<?> updateTimeSheet(@RequestBody TimesheetDTO reqBody){
  
  long tId = reqBody.getTId(); 
  TimesheetEntity timesheetEntity = adminService.getTimeSheetByTId(tId); 
  System.out.println("GOT TIMESHEETENTITY, We still good");
  if(timesheetEntity == null){
    ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("No record with the given eId & tId").build();
      return ResponseEntity.badRequest().body(responseDTO);
  }

  try {
   if(reqBody.isApproved()){
     timesheetEntity.setApproved(true);
   }else {
     timesheetEntity.setRejected(true);
   }

   adminService.updateTimeSheet(timesheetEntity); 
   return ResponseEntity.ok().body("SUCCESSFULLY SUBMITTED");                   
    }

  catch(Exception exception){
    exception.printStackTrace();

  }

  return null; 

}

@PutMapping(value="/updateEmployeeInfo", produces = "application/json")
@ResponseBody
public ResponseEntity<?> updateEmployeeInfo(@RequestBody EmployeeDTO reqBody){
  long eId = reqBody.getId(); 
  EmployeeEntity employeeEntity = adminService.getEmployeeEntityByEId(eId); 

  if(employeeEntity == null){
    ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("No record with the given eId & tId").build();
      return ResponseEntity.badRequest().body(responseDTO);
  }

  try {
   
    if(reqBody.isApproved())
      employeeEntity.setApproved(true);
    else
      employeeEntity.setRejected(true);
    adminService.updateEmployeeInfo(employeeEntity);
    return ResponseEntity.ok().body("SUCESSFULLY SUBMITTED");

  }
  catch(Exception exception){
    exception.printStackTrace();

  }

  return null; 

}

// @PostMapping(value = "/deleteEmployee", produces = "application/json")
// @ResponseBody
// public String deleteEmployee(@RequestParam(value = "employeeID") String employeeID) {
//   try{
//       return adminService.deleteEmployee(employeeID);
//   }
//   catch(Exception exception){
//       exception.printStackTrace();
//   }

//   return null; 
// }




  // @PostMapping("/signup") // needs to be updated with user password and more info
  // public ResponseEntity<?> registerUser(@RequestBody EmployeeDTO employeeDTO) {
  //   try {
  //     EmployeeEntity employee = AdminEntity.builder()//
  //         .email(employeeDTO.getEmail())//
  //         .firstName(employeeDTO.getFirstName())//
  //         .lastName(employeeDTO.getLastName())//
  //         .build();

  //     EmployeeEntity registeredEmployee = employeeService.create(employee);
  //     log.info("\n ->  New user registered. Email: {}", registeredEmployee.getEmail());

  //     EmployeeDTO responseEmployeeDTO = EmployeeDTO.builder()//
  //         .email(registeredEmployee.getEmail())//
  //         .firstName(registeredEmployee.getFirstName())//
  //         .lastName(registeredEmployee.getLastName())//
  //         .build();

  //     return ResponseEntity.ok().body(responseEmployeeDTO);

  //   } catch (Exception e) {
  //     ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error(e.getMessage()).build();
  //     return ResponseEntity.badRequest().body(responseDTO);
  //   }
  // }

}