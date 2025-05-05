package me.sathish.trackgarmin.entities;

import jakarta.persistence.*;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "garmin_runs", schema = "runs_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarminRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private BigInteger activityID;

    @Column(nullable = false)
    private String activityDate;

    @Column(nullable = false)
    private String activityType;

    @Column(nullable = false)
    private String activityDescription;

    @Column(nullable = false)
    private String elapsedTime;

    @Column(nullable = false)
    private String distance;

    private String calories;

    private String maxHeartRate;

    @Column(nullable = false)
    private String activityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(nullable = false, name = "created_at")
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
        GarminRun garminRun = (GarminRun) o;
        return id != null && Objects.equals(id, garminRun.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
