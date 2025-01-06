package task.manager.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.manager.api.rest.dto.response.TaskResponseDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
    private LocalDateTime updateDate;
    private Long userId;

    public TaskResponseDTO toDTO() {
        return TaskResponseDTO.builder()
                              .id(this.id)
                              .title(this.title)
                              .description(this.description)
                              .status(this.status)
                              .dueDate(this.dueDate)
                              .updateDate(this.updateDate)
                              .userId(this.userId)
                              .build();
    }

}
