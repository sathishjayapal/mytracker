package me.sathish.trackgarmin.repositories;

import me.sathish.trackgarmin.entities.GarminRun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarminRunRepository extends JpaRepository<GarminRun, Long> {
    Page<GarminRun> findGarminRunsByActivityNameContainingIgnoreCase(String query, Pageable pageable);
}
