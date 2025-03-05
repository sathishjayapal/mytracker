package me.sathish.garmindatainitializer.tracker.repos;

import java.util.Optional;
import me.sathish.garmindatainitializer.tracker.data.EventTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileNameTrackerRepository extends JpaRepository<EventTracker, Long> {
    Optional<EventTracker> findFileNameTrackerByFilename(String fileName);
}
