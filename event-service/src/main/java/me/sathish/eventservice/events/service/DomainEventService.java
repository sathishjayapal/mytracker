package me.sathish.eventservice.events.service;


import me.sathish.eventservice.domain.data.Domain;
import me.sathish.eventservice.domain.repo.DomainRepo;
import me.sathish.eventservice.events.data.DomainEvent;
import me.sathish.eventservice.events.data.DomainEventDTO;
import me.sathish.eventservice.events.data.DomainEventMapper;
import me.sathish.eventservice.events.repo.DomainEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DomainEventService {
    private final DomainEventRepo domainEventRepo;
    private final DomainRepo domainRepo;
    private final DomainEventMapper domainEventMapper;

    @Autowired
    public DomainEventService(
            DomainEventRepo domainEventRepo, DomainRepo domainRepo, DomainEventMapper domainEventMapper) {
        this.domainEventRepo = domainEventRepo;
        this.domainRepo = domainRepo;
        this.domainEventMapper = domainEventMapper;
    }

    @Transactional
    public DomainEventDTO createDomainEvent(DomainEventDTO domainEventDTO) {
        Optional<Domain> domain = domainRepo.findByDomainName(domainEventDTO.getDomainName());
        if (domain.isPresent()) {
            DomainEvent domainEvent = domainEventMapper.toEntity(domainEventDTO, domain.get());
            domainEvent = domainEventRepo.persist(domainEvent);
            return domainEventMapper.toDto(domainEvent);
        } else {
            throw new IllegalArgumentException("Domain not found: " + domainEventDTO.getDomainName());
        }
    }

    public DomainEventDTO getDomainEvent(Long id) {
        Optional<DomainEvent> domainEvent = domainEventRepo.findById(id);
        return domainEvent.map(domainEventMapper::toDto).orElse(null);
    }
    public List<DomainEventDTO> getDomainEvents() {
        Domain domain = domainRepo.findByDomainName("GARMIN").orElseThrow(() -> new IllegalArgumentException("Domain not found: GARMIN"));
        return domainEventRepo.getDomainEventsByDomainName(domain).stream().map(domainEventMapper::toDto).collect(Collectors.toList());
    }
}
