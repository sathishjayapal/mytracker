package me.sathish.trackstrava.repositories;

import me.sathish.trackstrava.entities.StravaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StravaUserRepository extends JpaRepository<StravaUser, Long> {}
