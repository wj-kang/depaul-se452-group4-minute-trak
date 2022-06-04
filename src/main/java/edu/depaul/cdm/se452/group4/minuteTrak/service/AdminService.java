package edu.depaul.cdm.se452.group4.minuteTrak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.AdminDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.EmployeeDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.ApprovedTimeOffEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;

import edu.depaul.cdm.se452.group4.minuteTrak.persistence.AdminRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.ApprovedTimeOffRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.EmployeeRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimeOffRequestRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimesheetRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AdminService {

  private AdminRepository adminRepository;

  private TimeOffRequestRepository timeOffRequestRepository;

  private TimesheetRepository timesheetRepository;
 
  private EmployeeRepository employeeRepository;

  private ApprovedTimeOffRepository approvedTimeOffRepository; 

  @Autowired
  public AdminService(AdminRepository adminRepository, TimeOffRequestRepository timeOffRequestRepository, 
  TimesheetRepository timesheetRepository, EmployeeRepository employeeRepository) {
    this.adminRepository = adminRepository;
    this.timeOffRequestRepository = timeOffRequestRepository;
    this.timesheetRepository = timesheetRepository;
    this.employeeRepository = employeeRepository; 
  }


  public AdminEntity getByCredentials(String email, String password, PasswordEncoder encoder) {
    AdminEntity originalUser = adminRepository.findByEmail(email); 

    if(originalUser != null && encoder.matches(password,  originalUser.getPassword())){
      System.out.println("inside first if statement");
      return originalUser; 
    }
    return null; 
  }

  public List<TimeOffRequestEntity> getAllTimeOffRequests(){
        return timeOffRequestRepository.findAll();
  }


 public List<TimesheetEntity> getAllTimeSheets(){
   System.out.println("INSIDE service getAllTimeSheets");
      return timesheetRepository.findAll(); 
  }

public List<EmployeeEntity> getAllEmployees() {
  List<EmployeeEntity> empList = null;
  try{
      empList = employeeRepository.findAll();
      System.out.println(empList.toString());
  }
  catch(Exception exception){
    exception.printStackTrace();
  }
  return empList; 
}

public EmployeeEntity getEmployeeData(String emplyeeString) {
      return employeeRepository.findByEmail(emplyeeString);
}

public String createEmployee(EmployeeEntity employeeEntity){ //might have to make this entity!!
  try{   
  EmployeeEntity e = employeeRepository.save(employeeEntity);
  return (e.toString());
  }
  catch(Exception ex){
    ex.printStackTrace();
    return null;
  }

  
}

public TimeOffRequestEntity updateTimeRequest(TimeOffRequestEntity timeOffRequestEntity){
  try{
  TimeOffRequestEntity t = timeOffRequestRepository.save(timeOffRequestEntity);
  return t;
}
catch(Exception ex){
   ex.printStackTrace();
   return null; 
}
}
 

public String updateTimeSheet(TimesheetEntity timesheetEntity){
try{
    TimesheetEntity t = timesheetRepository.save(timesheetEntity);
  return t.toString();
}
catch(Exception ex){
   ex.printStackTrace();
   return null; 
}
}

public String updateEmployeeInfo(EmployeeEntity employeeEntity){
  try{
    EmployeeEntity t = employeeRepository.save(employeeEntity);
  return t.toString();
}
catch(Exception ex){
   ex.printStackTrace();
   return null; 
}
}

public String deleteEmployee(long employeeID){
  try{
    employeeRepository.deleteById(employeeID);;
  return "Success";
}
catch(Exception ex){
   ex.printStackTrace();
   return null; 
}
}

public TimeOffRequestEntity getTimeOffRequestEntityByRId(long rId) {
  TimeOffRequestEntity entity = timeOffRequestRepository.getById(rId);
  if (entity == null) {
    // If there is no entity with the given tId,
    // or if eId is not matched with the found entity, return null
    return null;
  } 
  return entity;
}

public EmployeeEntity getEmployeeEntityByEId(long eId) {
  EmployeeEntity entity = employeeRepository.getById(eId);
  if (entity == null) {
    // If there is no entity with the given tId,
    // or if eId is not matched with the found entity, return null
    return null;
  } 
  return entity;
}

public TimesheetEntity getTimeSheetByTId(long tId) {
  TimesheetEntity entity = timesheetRepository.getById(tId);
  if (entity == null) {
    // If there is no entity with the given tId,
    // or if eId is not matched with the found entity, return null
    return null;
  } 
  return entity;
}

public List<ApprovedTimeOffEntity> addApprovedTimeOffs(List<ApprovedTimeOffEntity> approvedTimeOffEntities){
  
  return approvedTimeOffRepository.saveAll(approvedTimeOffEntities); 
  
}



}
