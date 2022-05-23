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
@Table(name = "time_off_requests")
public class TimeOffRequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "req_id")
  private long reqId;

  @Column(name = "from_date", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime fromDate;

  @Column(name = "to_date", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime toDate;

  @Column(name = "is_paid", nullable = false)
  private boolean isPaid;

  @Column(name = "is_approved")
  private boolean isApproved;

  /* Employee(1) -> TimeOffRequests(*) */
  @ManyToOne
  @JoinColumn(name = "e_id")
  private EmployeeEntity employee;

  // TimeOffRequest(1) -> ApprovedTimeOffs(*)
  @OneToMany(mappedBy = "timeOffRequest")
  private List<ApprovedTimeOffEntity> approvedTimeOffs;

}
