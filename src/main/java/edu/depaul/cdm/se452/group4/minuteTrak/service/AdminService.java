package edu.depaul.cdm.se452.group4.minuteTrak.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.AdminDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.EmployeeDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;

import antlr.collections.List;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.AdminRepository;
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
      return originalUser; 
    }
    return null; 
  }

  public List<TimeOffRequestEntity> getAllTimeOffRequests(){
        return timeOffRequestRepository.findAll();
  }


 public List<TimesheetEntity> getAllTimeSheets(){
      return timesheetRepository.findAll(); 
  }

public List<EmployeeEntity> getAllEmployees() {
      return employeeRepository.findAll();
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

public String updateTimeRequest(TimeOffRequestEntity timeOffRequestEntity){
  try{
  TimeOffRequestEntity t = timeOffRequestRepository.save(timeOffRequestEntity);
  return t.toString();
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

public String deleteEmployee(Long employeeID){
  try{
    employeeRepository.deleteById(employeeID);;
  return "Success";
}
catch(Exception ex){
   ex.printStackTrace();
   return null; 
}
}



}
