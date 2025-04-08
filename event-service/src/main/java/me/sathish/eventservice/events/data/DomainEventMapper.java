package me.sathish.eventservice.events.data;


import me.sathish.eventservice.domain.data.Domain;
import org.springframework.stereotype.Component;

@Component
public class DomainEventMapper {
    public DomainEvent toEntity(DomainEventDTO dto, Domain domain) {
        if (dto == null || domain == null) {
            return null;
        }

        DomainEvent domainEvent = new DomainEvent();
        domainEvent.setId(dto.getId());
        domainEvent.setEventId(dto.getEventId());
        domainEvent.setEventType(dto.getType());
        domainEvent.setPayload(dto.getPayload());
        domainEvent.setDomainName(domain);
        return domainEvent;
    }

    public DomainEventDTO toDto(DomainEvent entity) {
        if (entity == null) {
            return null;
        }

        DomainEventDTO dto = new DomainEventDTO();
        dto.setId(entity.getId());
        dto.setEventId(entity.getEventId());
        dto.setType(entity.getEventType());
        dto.setPayload(entity.getPayload());
        dto.setDomainName(entity.getDomainName().getDomainName());
        dto.setEventRecordedAt(entity.getCreatedAt());
        return dto;
    }
}
