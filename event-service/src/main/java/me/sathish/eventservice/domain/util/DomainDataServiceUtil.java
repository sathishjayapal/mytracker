package me.sathish.eventservice.domain.util;


import me.sathish.eventservice.domain.data.Domain;
import me.sathish.eventservice.domain.repo.DomainRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DomainDataServiceUtil {
    private final DomainRepo domainRepo;

    public DomainDataServiceUtil(DomainRepo domainRepo) {
        this.domainRepo = domainRepo;
    }

    public void loadDomainData() {
        if (domainRepo.count() > 0) {
            return;
        }

        List<Domain> domainList = Arrays.asList(
                new Domain("GARMIN", "Active", "Garmin domain related data"),
                new Domain("NIKE", "Inactive", "Nike domain related data"),
                new Domain("APPLE", "Active", "Apple domain related data"));

        domainRepo.persistAll(domainList);
    }
}
