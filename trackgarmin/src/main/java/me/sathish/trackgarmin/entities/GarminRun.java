package me.sathish.trackgarmin.entities;

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
@Table(name = "garmin_runs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarminRun {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String text;

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
