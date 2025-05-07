package me.sathish.trackstrava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "strava_runs", schema = "runs_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StravaRun{
    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "run_number")
    private Long runNumber;

    @Column(nullable = false)
    private String run_name;

    @Column(nullable = false)
    private LocalDateTime run_date;

    @Column(nullable = false)
    private int miles;

    @Column(nullable = false)
    private Long start_location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;


    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private Instant updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private BigInteger updatedBy;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StravaRun stravaRun = (StravaRun) o;
        return runNumber != null && Objects.equals(runNumber, stravaRun.runNumber);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
