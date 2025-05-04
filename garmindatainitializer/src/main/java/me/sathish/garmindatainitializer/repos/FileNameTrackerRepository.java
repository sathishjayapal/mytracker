package me.sathish.garmindatainitializer.repos;

import me.sathish.garmindatainitializer.data.FileNameTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FileNameTrackerRepository extends JpaRepository<FileNameTracker, Long> {
    Optional<FileNameTracker> findByFileName(String orderNumber);
}

