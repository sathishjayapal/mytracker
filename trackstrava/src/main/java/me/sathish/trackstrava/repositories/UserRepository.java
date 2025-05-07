package me.sathish.trackstrava.repositories;

import me.sathish.trackstrava.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
