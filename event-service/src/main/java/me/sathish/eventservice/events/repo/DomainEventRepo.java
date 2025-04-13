package me.sathish.eventservice.events.repo;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import me.sathish.eventservice.domain.data.Domain;
import me.sathish.eventservice.events.data.DomainEvent;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface DomainEventRepo
        extends BaseJpaRepository<DomainEvent, Long>, ListPagingAndSortingRepository<DomainEvent, Long> {
    Collection<DomainEvent> getDomainEventsByDomainName(@NotNull Domain domainName);
}
