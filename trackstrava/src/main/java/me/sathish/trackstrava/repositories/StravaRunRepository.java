package me.sathish.trackstrava.repositories;

import me.sathish.trackstrava.entities.StravaRun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StravaRunRepository extends JpaRepository<StravaRun, Long> {
    @Query("select p from StravaRun p ")
    List<StravaRun> findAllByRunNumber();
}
