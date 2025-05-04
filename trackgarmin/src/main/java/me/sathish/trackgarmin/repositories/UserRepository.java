package me.sathish.trackgarmin.repositories;

import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
