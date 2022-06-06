package edu.depaul.cdm.se452.group4.minuteTrak.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import antlr.collections.List;
import edu.depaul.cdm.se452.group4.minuteTrak.model.ApprovedTimeOffEntity;
import io.micrometer.core.ipc.http.HttpSender.Method;

@Repository
public interface ApprovedTimeOffRepository extends JpaRepository<ApprovedTimeOffEntity, Long> {

}
