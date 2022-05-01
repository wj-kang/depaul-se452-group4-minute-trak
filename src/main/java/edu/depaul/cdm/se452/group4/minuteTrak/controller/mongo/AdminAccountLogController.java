package edu.depaul.cdm.se452.group4.minuteTrak.controller.mongo;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.depaul.cdm.se452.group4.minuteTrak.model.mongo.AdminAccountLog;
import edu.depaul.cdm.se452.group4.minuteTrak.service.mongo.AdminAccountLogService;


@RestController
@RequestMapping("/admin/account")
public class AdminAccountLogController {
  private final AdminAccountLogService adminAccountLogService;

  @Autowired
  public AdminAccountLogController(AdminAccountLogService adminAccountLogService) {
    this.adminAccountLogService = adminAccountLogService;
  }

  @PostMapping("/")
  public void save(@RequestBody AdminAccountLog log) {
    adminAccountLogService.save(log);
  }

  @GetMapping("/")
  public List<AdminAccountLog> findAll() {
    return adminAccountLogService.findAll();
  }
}
