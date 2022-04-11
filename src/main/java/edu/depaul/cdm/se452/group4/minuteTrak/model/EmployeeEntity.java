package edu.depaul.cdm.se452.group4.minuteTrak.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
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
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String firstname;

  @Column(nullable = false)
  private String lastname;

  // @Column(nullable = false)
  // private String password;

}
