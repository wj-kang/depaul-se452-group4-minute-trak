package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Works")
public class WorksEntity {

  /*
   * For this table. Primary key => e_id + p_id + date
   * 
   */


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "p_id")
  private long pId;

  @Column(name = "e_id")
  private long eId;

  @Column(name = "t_id")
  private long tId;


  @Column(nullable = false)
  private int hours;

  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime date;

  // /* Works(1) -> Employees(*) */
  // @OneToMany(mappedBy = "project")
  // private List<ProjectsEntity> projects;

  // /* Employee(1) -> Works(*) */
  // @OneToMany(mappedBy = "employee")
  // private List<EmployeeEntity> projects;

}

