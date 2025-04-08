package me.sathish.eventservice.events.data;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import me.sathish.eventservice.data.BaseEvents;
import me.sathish.eventservice.domain.data.Domain;

@Getter
@Setter
@Entity
@Table(name = "domain_events", schema = "runeventsprojectschema")
@AttributeOverrides({
    @AttributeOverride(name = "createdAt", column = @Column(name = "created_at", nullable = false)),
    @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at"))
})
public class DomainEvent extends BaseEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domain_event_id_seq")
    @SequenceGenerator(name = "domain_event_id_seq", sequenceName = "runeventsprojectschema.event_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "domain_name", nullable = false, referencedColumnName = "domain_name")
    private Domain domainName;

    @NotNull
    @Column(name = "event_id", nullable = false, length = Integer.MAX_VALUE)
    private String eventId;

    @NotNull
    @Column(name = "event_type", nullable = false, length = Integer.MAX_VALUE)
    private String eventType;

    @NotNull
    @Column(name = "payload", nullable = false, length = Integer.MAX_VALUE)
    private String payload;
}
