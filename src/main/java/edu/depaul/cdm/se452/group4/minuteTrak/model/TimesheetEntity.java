package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate endDate;

  @Column(name = "is_submitted")
  private boolean isSubmitted;

  @Column(name = "is_approved")
  private boolean isApproved;

  @Column(name = "is_rejected")
  private boolean isRejected;

  // timehsheet(1) -> works(*)
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "timesheet")
  private List<WorkEntity> works;

  // timesheet(*) -> employee(1)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "e_id")
  private EmployeeEntity employee;

}

