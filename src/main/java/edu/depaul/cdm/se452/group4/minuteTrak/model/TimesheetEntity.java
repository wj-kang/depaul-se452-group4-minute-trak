package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Timesheets")
public class TimesheetEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "t_id")
  private long tId;

  @Column(name = "start_date", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime endDate;

  @Column(name = "is_submitted")
  private String isSubmitted;

  @Column(name = "is_approved")
  private String isApproved;

  // timesheet(1) -> approvedTimeOffs(*)
  @OneToMany(mappedBy = "timesheet")
  private List<ApprovedTimeOffEntity> approvedTimeOffs;

  // timehsheet(1) -> works(*)
  @OneToMany(mappedBy = "timesheet")
  private List<WorkEntity> works;

  // timesheet(*) -> employee(1)
  @ManyToOne
  @JoinColumn(name = "e_id")
  private EmployeeEntity employee;

}

