package edu.depaul.cdm.se452.group4.minuteTrak.model.mongo;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("admin_account_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminAccountLog {
  @Id
  private String id;

  @Field(name = "admin_id")
  private int adminId;

  @Field
  private String task;

  @Field(name = "e_id")
  private int eId;

  @Field
  private String result;

  @Field(name = "created_at")
  private LocalDateTime createdAt;
}
