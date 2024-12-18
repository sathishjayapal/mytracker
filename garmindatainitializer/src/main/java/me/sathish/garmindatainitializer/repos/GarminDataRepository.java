package me.sathish.garmindatainitializer.repos;

import me.sathish.garmindatainitializer.data.GarminRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarminDataRepository extends JpaRepository<GarminRun, Long> {
}
