package task.manager.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.domain.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
