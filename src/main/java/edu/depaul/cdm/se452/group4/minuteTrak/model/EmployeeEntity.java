package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.time.LocalDate;
import java.util.Date;
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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "Employees", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class EmployeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "e_id")
  private long eId;

  @Column(nullable = false)
  private String email;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dob;

  @Column(nullable = false)
  private String phone;

  @Column(name = "created_time")
  @CreationTimestamp
  private Date createdTime;

  @Column(name = "is_approved")
  private boolean isApproved;

  @Column(name = "is_rejected")
  private boolean isRejected;

  @Column(name = "pto_bank")
  private int ptoBank;

  /* Employee(*) -> Salary(1) */
  @ManyToOne
  @JoinColumn(name = "salary_level")
  private SalaryEntity salary;

  /* Employee(*) -> Admin(1) */
  @ManyToOne
  @JoinColumn(name = "managed_by_admin_id", referencedColumnName = "admin_id")
  private AdminEntity managedByAdminId;

  /* Employee(1) -> TimeOffRequest(*) */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
  private List<TimeOffRequestEntity> timeOffRequests;

  /* Employee(1) -> Timesheets(*) */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
  private List<TimesheetEntity> timesheets;

}


