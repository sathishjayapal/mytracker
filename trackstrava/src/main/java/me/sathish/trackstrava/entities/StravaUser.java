package me.sathish.trackstrava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "strava_user" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StravaUser extends StravaMSBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StravaUser stravaUser = (StravaUser) o;
        return customerId != null && Objects.equals(customerId, stravaUser.customerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
