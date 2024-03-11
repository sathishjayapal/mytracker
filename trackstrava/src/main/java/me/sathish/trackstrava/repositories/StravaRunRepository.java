package me.sathish.trackstrava.repositories;

import java.util.List;
import me.sathish.trackstrava.entities.StravaRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StravaRunRepository extends JpaRepository<StravaRun, Long> {
    @Query("select p from StravaRun p ")
    List<StravaRun> findAllByRunNumber();
}
