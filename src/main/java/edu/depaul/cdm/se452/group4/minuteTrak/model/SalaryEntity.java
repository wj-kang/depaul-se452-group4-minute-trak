package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Salaries")
public class SalaryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "salary_level")
  private long salaryLevel;

  @Column(nullable = false)
  private int hourly;

  /* Salary(1) -> Employee(*) */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "salary")
  private List<EmployeeEntity> employees;

}

