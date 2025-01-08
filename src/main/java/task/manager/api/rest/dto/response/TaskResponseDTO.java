package task.manager.api.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.manager.api.rest.dto.request.TaskRequestUpdateDTO;
import task.manager.domain.entity.Task;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
    private LocalDateTime updateDate;
    private Long userId;

    public Task toUpdate(TaskRequestUpdateDTO requestUpdateDTO) {
        return Task.builder()
                   .id(this.id)
                   .title(requestUpdateDTO.getTitle())
                   .description(requestUpdateDTO.getDescription())
                   .status(requestUpdateDTO.getStatus())
                   .dueDate(this.dueDate)
                   .updateDate(LocalDateTime.now())
                   .build();
    }
}
