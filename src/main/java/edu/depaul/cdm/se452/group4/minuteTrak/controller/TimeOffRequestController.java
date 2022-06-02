package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group4.minuteTrak.model.EmployeeEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimeOffRequestRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.security.AuthStatus;
import edu.depaul.cdm.se452.group4.minuteTrak.service.TimeOffRequestService;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import javax.xml.crypto.dsig.TransformService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TimeOffRequestController {

    // @Autowired
    // private TimeOffRequestRepository timeOffRequestRepository;

    @Autowired
    private TimeOffRequestService timeOffRequestService;

    public TimeOffRequestController(TimeOffRequestService timeOffRequestService) {
        this.timeOffRequestService = timeOffRequestService;
    }

    @PostMapping("/timeoff")
    public ResponseEntity<TimeOffRequestEntity> create(@RequestBody TimeOffRequestEntity newTimeOffRequest) {
        if (newTimeOffRequest != null) {
            TimeOffRequestEntity savedTimeOffRequestEntity = timeOffRequestService.create(newTimeOffRequest);
            return ResponseEntity.ok().body(savedTimeOffRequestEntity);
        } else {
            return ResponseEntity.badRequest().body(newTimeOffRequest);
        }

    }

    @GetMapping("/timeoff/{request_id}")
    public ResponseEntity<?> findById(@PathVariable("request_id") Long reqId) {
        Optional<TimeOffRequestEntity> timeOffRequestEntityOpt = timeOffRequestService.findById(reqId);

        if (timeOffRequestEntityOpt.isPresent()) {
            return ResponseEntity.ok().body(timeOffRequestEntityOpt.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/timeoff/list")
    // public List<TimeOffRequestEntity> getTimeOffRequestList()) {
    public ResponseEntity<?> findAll(@AuthenticationPrincipal AuthStatus authStatus) {

        final String authRole = authStatus.getRole();
        List<TimeOffRequestEntity> timeOffRequestEntities;


        switch (authRole) {
            case "admin":
                timeOffRequestEntities = timeOffRequestService.findAll();
                return ResponseEntity.ok().body(timeOffRequestEntities);

            case "employee":
               timeOffRequestEntities = timeOffRequestService.findAll();
                return ResponseEntity.ok().body(timeOffRequestEntities);

            default:
                return ResponseEntity.badRequest().body("err");
        }

        // if (authRole != "employee") {

        // }
        // final int authId = authStatus.getId();
        // List<TimeOffRequestEntity> timeOffRequestEntities =
        // timeOffRequestRepository.getListById(authId);

    }

}
