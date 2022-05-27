package edu.depaul.cdm.se452.group4.minuteTrak.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
// import edu.depaul.cdm.se452.group4.minuteTrak.dto.AdminDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.EmployeeDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimesheetEntity;

import antlr.collections.List;
import edu.depaul.cdm.se452.group4.minuteTrak.model.AdminEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.AdminRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService {

  private AdminRepository adminRepository;

  @Autowired
  public AdminService(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  public ArrayList<TimeOffRequestEntity> getAllTimeOffRequests(){
        return null;
  }


 public ArrayList<TimesheetEntity> getAllTimeSheets(){
      return null; 
  }

public ArrayList<EmployeeEntity> getAllEmployees() {
    return null;
}

public EmployeeEntity getEmployeeData(String emplyeeString) {
  return null;
}

public String createEmployee(EmployeeDTO employeeDTO){
  return null; 
}

public String updateTimeRequest(TimeOffRequestEntity timeOffRequestEntity){
  return null; 
}

public String updateTimeSheet(TimesheetEntity timesheetEntity){
  return null; 
}

public String updateEmployeeInfo(EmployeeEntity employeeEntity){
  return null; 
}

public String deleteEmployee(String employeeID){
  return null; 
}



}
