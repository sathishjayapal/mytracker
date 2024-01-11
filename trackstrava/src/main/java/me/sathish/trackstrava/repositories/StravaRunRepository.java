package me.sathish.trackstrava.repositories;

import me.sathish.trackstrava.entities.StravaRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StravaRunRepository extends JpaRepository<StravaRun, Long> {}
