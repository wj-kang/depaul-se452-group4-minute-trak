package edu.depaul.cdm.se452.group4.minuteTrak.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ApprovedTimeOff")
public class ApprovedTimeOffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_id")
    private long eId;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime date;

    @Column(name = "is_paid")
    private boolean isPaid;

    // RELATIONSHIP
    // TimeOffRequest(1) -> ApprovedTimeOffs(*)
    /* Employee(*) -> Salary(1) */
    @ManyToOne
    @JoinColumn(name = "req_id")
    private TimeOffRequestEntity timeOffRequest;
    // imesheet
    t
}
