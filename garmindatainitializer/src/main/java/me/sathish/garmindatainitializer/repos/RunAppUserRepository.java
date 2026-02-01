package me.sathish.garmindatainitializer.repos;

import java.util.Optional;
import me.sathish.garmindatainitializer.data.RunAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunAppUserRepository extends JpaRepository<RunAppUser, Long> {
    Optional<RunAppUser> findByEmail(String email);
}
