package me.sathish.garmindatainitializer.repos;

import java.util.Optional;
import me.sathish.garmindatainitializer.data.FileNameTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileNameTrackerRepository extends JpaRepository<FileNameTracker, Long> {
    // Changed method name to match the NamedQuery convention
    Optional<FileNameTracker> findByFileName(String fileName);
}
