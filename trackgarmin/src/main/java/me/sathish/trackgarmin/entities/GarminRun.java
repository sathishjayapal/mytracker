package me.sathish.trackgarmin.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Entity
@Table(name = "garmin_runs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarminRun extends GarminMSBaseEntity {
    @Id
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "run_date", nullable = false)
    private LocalDateTime runDateTime;

    @Column(name = "miles", nullable = false)
    private BigDecimal runMiles;

    @Column(nullable = false)
    private String runName;

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
