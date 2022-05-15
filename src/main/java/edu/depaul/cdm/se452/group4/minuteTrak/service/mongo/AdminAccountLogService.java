package edu.depaul.cdm.se452.group4.minuteTrak.service.mongo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.depaul.cdm.se452.group4.minuteTrak.model.mongo.AdminAccountLog;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.mongo.AdminAccountLogRepository;

@Service
public class AdminAccountLogService {
  private final AdminAccountLogRepository adminAccountLogRepository;

  @Autowired
  public AdminAccountLogService(AdminAccountLogRepository adminAccountLogRepository) {
    this.adminAccountLogRepository = adminAccountLogRepository;
  }

  public void save(AdminAccountLog log) {
    adminAccountLogRepository.insert(log);
  }

  public List<AdminAccountLog> findAll() {
    return adminAccountLogRepository.findAll();
  }
}
