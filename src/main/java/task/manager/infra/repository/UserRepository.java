package task.manager.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.manager.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
