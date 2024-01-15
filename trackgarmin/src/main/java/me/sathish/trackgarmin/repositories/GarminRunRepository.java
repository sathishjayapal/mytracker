package me.sathish.trackgarmin.repositories;

import me.sathish.trackgarmin.entities.GarminRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarminRunRepository extends JpaRepository<GarminRun, Long> {}
