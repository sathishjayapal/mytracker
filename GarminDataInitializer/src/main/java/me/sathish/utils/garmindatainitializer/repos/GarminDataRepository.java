package me.sathish.utils.garmindatainitializer.repos;

import me.sathish.utils.garmindatainitializer.data.GarminRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarminDataRepository extends JpaRepository<GarminRun, Long> {
}
