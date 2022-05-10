package edu.depaul.cdm.se452.group4.minuteTrak.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("timesheets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timesheets {

  @Id
  private String id;

  @Field(name = "t_id")
  private int tId;

  @Field(name = "start_date")
  private String startDate;

  @Field(name = "end_date")
  private String endDate;

  @Field(name = "is_submitted")
  private boolean isSubmitted;

  @Field(name = "is_approved")
  private boolean isApproved;

  @Field
  private Object pto;

  @Field
  private Object works;
}
