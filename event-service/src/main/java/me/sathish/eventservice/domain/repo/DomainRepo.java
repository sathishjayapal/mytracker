package me.sathish.eventservice.domain.repo;


import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import me.sathish.eventservice.domain.data.Domain;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface DomainRepo extends BaseJpaRepository<Domain, Long>, ListPagingAndSortingRepository<Domain, Long> {
    Optional<Domain> findByDomainName(String domainName);
}
