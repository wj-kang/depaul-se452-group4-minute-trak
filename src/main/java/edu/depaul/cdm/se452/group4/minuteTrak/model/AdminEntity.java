package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "Admins")
public class AdminEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "admin_id")
  private long adminId;

  @Column(nullable = false)
  private String account;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "managedByAdminId")
  private List<EmployeeEntity> employees;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "e_id")
  private EmployeeEntity employee;

}
