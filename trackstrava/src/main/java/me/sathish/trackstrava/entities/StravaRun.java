package me.sathish.trackstrava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class StravaRun {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StravaRun stravaRun = (StravaRun) o;
        return id != null && Objects.equals(id, stravaRun.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
