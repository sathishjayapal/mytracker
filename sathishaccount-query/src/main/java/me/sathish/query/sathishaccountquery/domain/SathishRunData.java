package me.sathish.query.sathishaccountquery.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Entity(name = "sathish_run_data")
@Data
@Transactional(readOnly = true)
public class SathishRunData extends SathishRunMSBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private BigDecimal totalMiles;

    public SathishRunData() {}

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SathishRunData record = (SathishRunData) o;
        return id != null && Objects.equals(id, record.id);
    }

    public SathishRunData(BigDecimal totalMiles) {
        this.totalMiles = totalMiles;
    }
}
