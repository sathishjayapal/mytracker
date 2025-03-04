package me.sathish.garmindatainitializer.data;

import jakarta.persistence.*;
import java.math.BigInteger;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Entity
@Table(name = "garmin_runs", schema = "garminrunsschema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarminRun extends GarminMSBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private BigInteger activityID;

    @Column(nullable = false)
    private String activityDate;

    @Column(nullable = false)
    private String activityType;

    private String activityDescription;

    @Column(nullable = false)
    private String elapsedTime;

    @Column(nullable = false)
    private String distance;

    private String calories;

    private String maxHeartRate;

    @Column(nullable = false)
    private String activityName;

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
