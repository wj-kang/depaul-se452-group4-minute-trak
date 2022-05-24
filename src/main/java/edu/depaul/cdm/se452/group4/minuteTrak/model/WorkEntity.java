package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Works")
public class WorkEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "w_id")
  private long wId;

  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  @Column(nullable = false)
  private int hours;

  // /* Works(*) -> Project(1) */
  @ManyToOne
  @JoinColumn(name = "p_id")
  private ProjectEntity project;

  // /* Works(*) -> timesheet(1) */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_id")
  @ToString.Exclude
  private TimesheetEntity timesheet;

}

