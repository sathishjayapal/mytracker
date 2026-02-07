package me.sathish.garmindatainitializer.repos;

import me.sathish.garmindatainitializer.data.RunnerAppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerAppRoleRepository extends JpaRepository<RunnerAppRole, Long> {

    RunnerAppRole findByRoleNameIgnoreCase(String roleName);
}
