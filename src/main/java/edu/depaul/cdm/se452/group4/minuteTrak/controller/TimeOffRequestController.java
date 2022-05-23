package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimeOffRequestRepository;
import edu.depaul.cdm.se452.group4.minuteTrak.security.AuthStatus;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TimeOffRequestRepository timeOffRequestRepository;
    public TimeOffRequestController() {};
    
    @PostMapping("/timeoff")
    public Long createTimeOffRequestEntity(@RequestBody TimeOffRequestEntity newTimeOffRequest) {
        return timeOffRequestRepository.save(newTimeOffRequest).getReqId();
    }

    @GetMapping("/timeoff/{request_id}")
    public Optional<TimeOffRequestEntity> getDetail(@PathVariable("request_id") Long reqId) {
        return timeOffRequestRepository.findById(reqId);
    }

    @GetMapping("/timeoff/list")
    // public List<TimeOffRequestEntity> getTimeOffRequestList()) {
    public ResponseEntity<?> getTimeOffRequestList(@AuthenticationPrincipal AuthStatus authStatus) {
        final String authRole = authStatus.getRole();
        if (authRole != "employee") {

        }
        final int authId = authStatus.getId();
        // List<TimeOffRequestEntity> timeOffRequestEntities = timeOffRequestRepository.getListById(authId);

        List<TimeOffRequestEntity> timeOffRequestEntities = timeOffRequestRepository.findAll();
        return ResponseEntity.ok().body(timeOffRequestEntities);
    }
    
    
}

