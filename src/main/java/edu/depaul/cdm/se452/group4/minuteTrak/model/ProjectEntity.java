package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.io.Serializable;
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
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Projects")
public class ProjectEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "p_id")
  private long pId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int budget;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
  @ToString.Exclude
  private List<WorkEntity> works;

}
