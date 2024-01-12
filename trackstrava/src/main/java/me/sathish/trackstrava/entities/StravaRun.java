package me.sathish.trackstrava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Entity
@Table(name = "strava_runs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StravaRun extends StravaMSBaseEntity{
    @Column(name = "customer_id")
    private Long customerId;
    @Id
    private Long run_number;
    @Column(nullable = false)
    private String run_name;
    @Column(nullable = false)
    private LocalDateTime run_date;
    @Column(nullable = false)
    private int miles;
    @Column(nullable = false)
    private Long start_location;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StravaRun stravaRun = (StravaRun) o;
        return run_number != null && Objects.equals(run_number, stravaRun.run_number);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
