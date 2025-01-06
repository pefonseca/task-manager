package task.manager.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.domain.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
