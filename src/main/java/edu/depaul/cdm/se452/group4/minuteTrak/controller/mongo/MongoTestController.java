package edu.depaul.cdm.se452.group4.minuteTrak.controller.mongo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.depaul.cdm.se452.group4.minuteTrak.model.mongo.MongoTest;
import edu.depaul.cdm.se452.group4.minuteTrak.service.mongo.MongoTestService;

@RestController
@RequestMapping("/mongo")
public class MongoTestController {

  private final MongoTestService mongoTestService;

  @Autowired
  public MongoTestController(MongoTestService mongoTestService) {
    this.mongoTestService = mongoTestService;
  }

  @PostMapping("/post")
  public void api1(@RequestBody MongoTest mt) {
    mongoTestService.api1service(mt);
  }

  @GetMapping("/findall")
  public List<MongoTest> api2() {
    return mongoTestService.api2service();
  }

  // @PutMapping("/put")
  // public ResponseEntity api3(@RequestBody MongoTest mt) {
  // mongoTestService.api3service(mt);
  // return ResponseEntity.ok().build();

  // }
}
