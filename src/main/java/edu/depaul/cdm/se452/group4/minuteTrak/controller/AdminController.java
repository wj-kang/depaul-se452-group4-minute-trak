package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.AdminDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.EmployeeDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminService adminService;

// - getAllTimeOffRequests (needing approval) 
// - getAllTimeSheets (needing approval)
// - GetAllEmployees
// - GetEmployeesData - (Params: employee ID) 

// - updateTimeOffRequest
// - x
// - updateEmployeeInfo

// - createEmployee

// -deleteEmployee


@GetMapping(value = "/getAllTimeOffRequests", produces = "application/json")
@ResponseBody
public List<TimeOffRequestEntity> getAllTimeOffRequests(){
      List<TimeOffRequestEntity> requests = new ArrayList<>(); 

      try{
       requests = adminService.getAllTimeOffRequests(); 

      }
      catch(Exception ex){
        ex.printStackTrace();

      }

    return requests; 
}


@GetMapping(value = "/getAllTimeSheets", produces = "application/json")
@ResponseBody
public List<TimeSheetEntity> getAllTimeSheets(){
      List<TimeSheetEntity> sheets = new ArrayList<>(); 

      try{
        sheets = adminService.getAllTimeSheets();

      }
      catch(Exception ex){
        ex.printStackTrace();

      }

    return sheets; 
}

@GetMapping(value = "/getAllEmployees", produces = "application/json")
@ResponseBody
public List<EmployeeEntity> getAllEmployess(){
      List<EmployeeEntity> empList = new ArrayList<>(); 

      try{
          empList = adminService.getAllEmployees();
      }
      catch(Exception ex){
        ex.printStackTrace();

      }

    return empList; 
}

@GetMapping(value = "/getEmployeeData", produces = "application/json")
@ResponseBody
public EmployeeEntity getEmployeeData(
        @RequestParam(value = "employeeSelected") String employeeSelected
){
      EmployeeEntity emp; 

      try{
          emp = adminService.getEmployeeData(employeeSelected); 
      }
      catch(Exception ex){
        ex.printStackTrace();

      }

    return emp; 
}

@PostMapping(value = "/createEmployee", produces = "application/json", consumes = "application/json")
@ResponseBody
public String createEmployee(@RequestBody EmployeeDTO employeeDTO, HttpServletRequest request) {  
    try {
      HttpSession session = request.getSession(false); 
      String dlUserId = null; 
      if(session != null){
        dlUserId = (String) session.getAttribute("eId");
        employeeDTO.setId(dlUserId);
        return adminService.createEmployee(employeeDTO);
      }


    }
    catch(Exception exception){
      exception.printStackTrace();

    }

    return null; 
}

//this one needs to update timeOffReuqestEntity and create ApprovedTimeOffEntity
@PutMapping(value="/updateTimeRequest", produces = "application/json")
@ResponseBody
public String updateTimeRequest(@RequestBody TimeOffRequestEntity timeOffRequestEntity, HttpServletRequest request){
  try {
    HttpSession session = request.getSession(false); 
    long dlUserId; 
    if(session != null){
      dlUserId = (Long) session.getAttribute("eId");
      timeOffRequestEntity.setReqId(dlUserId);
    }

    return adminService.updateTimeRequest(timeOffRequestEntity); 
  
  }
  catch(Exception exception){
    exception.printStackTrace();

  }

  return null; 

}



@PutMapping(value="/updateTimesheet", produces = "application/json")
@ResponseBody
public String updateTimeSheet(@RequestBody TimesheetEntity timesheetEntity, HttpServletRequest request){
  try {
    HttpSession session = request.getSession(false); 
    long dlUserId; 
    if(session != null){
      dlUserId = (Long) session.getAttribute("tId");
      timesheetEntity.setTId(dlUserId);
    }

  }
  catch(Exception exception){
    exception.printStackTrace();

  }

  return null; 

}

@PutMapping(value="/updateEmployeeInfo", produces = "application/json")
@ResponseBody
public String updateEmployeeInfo(@RequestBody EmployeeEntity employeeEntity, HttpServletRequest request){
  try {
    HttpSession session = request.getSession(false); 
    long dlUserId; 
    if(session != null){
      dlUserId = (Long) session.getAttribute("tId");
      employeeEntity.setEId(dlUserId);
    }

  }
  catch(Exception exception){
    exception.printStackTrace();

  }

  return null; 

}

@PostMapping(value = "/deleteEmployee", produces = "application/json")
@ResponseBody
public String deleteEmployee(@RequestParam(value = "employeeID") String employeeID) {
  try{
      return adminService.deleteEmployee(employeeID);
  }
  catch(Exception exception){
      exception.printStackTrace();
  }

  return null; 
}




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
