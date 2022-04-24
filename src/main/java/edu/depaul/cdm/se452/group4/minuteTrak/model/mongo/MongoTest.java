package edu.depaul.cdm.se452.group4.minuteTrak.model.mongo;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("mongotest")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MongoTest {

  @Id
  private String id;

  @Field
  @Indexed(name = "email_unique", unique = true)
  private String email;

  @Field(name = "is_approved")
  private boolean isApproved;



}
