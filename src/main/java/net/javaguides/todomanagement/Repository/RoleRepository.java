package net.javaguides.todomanagement.Repository;

import net.javaguides.todomanagement.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
