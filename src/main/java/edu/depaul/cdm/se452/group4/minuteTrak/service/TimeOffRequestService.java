package edu.depaul.cdm.se452.group4.minuteTrak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.group4.minuteTrak.model.TimeOffRequestEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.persistence.TimeOffRequestRepository;


@Service
public class TimeOffRequestService {

    @Autowired
    private TimeOffRequestRepository timeOffRequestRepository;
    
    // public TimeOffRequestService(TimeOffRequestRepository timeOffRequestRepository) {
    //     this.timeOffRequestRepository = timeOffRequestRepository;
    // }
    
    public TimeOffRequestEntity create(TimeOffRequestEntity newTimeOffRequest) {
        return timeOffRequestRepository.save(newTimeOffRequest);
    }

    public Optional<TimeOffRequestEntity> findById(Long reqId) {
        return timeOffRequestRepository.findById(reqId);
    }

    public List<TimeOffRequestEntity> findAll() {
        return timeOffRequestRepository.findAll();
    }



}
