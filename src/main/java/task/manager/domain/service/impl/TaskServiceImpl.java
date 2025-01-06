package task.manager.domain.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import task.manager.api.rest.dto.request.TaskRequestDTO;
import task.manager.api.rest.dto.request.TaskRequestUpdateDTO;
import task.manager.api.rest.dto.response.TaskResponseDTO;
import task.manager.api.rest.dto.response.TaskResponseDeleteDTO;
import task.manager.domain.entity.Task;
import task.manager.domain.service.TaskService;
import task.manager.infra.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll().stream().map(task -> TaskResponseDTO.builder()
                                                                                 .id(task.getId())
                                                                                 .userId(task.getUserId())
                                                                                 .title(task.getTitle())
                                                                                 .status(task.getStatus())
                                                                                 .description(task.getDescription())
                                                                                 .dueDate(task.getDueDate())
                                                                                 .build())
                .toList();
    }

    @Override
    public TaskResponseDTO findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado nenhuma tarefa com esse ID.")).toDTO();
    }

    @Override
    public TaskResponseDTO create(TaskRequestDTO requestDTO) {
        Task taskDB = Task.builder()
                          .title(requestDTO.getTitle())
                          .description(requestDTO.getDescription())
                          .status(requestDTO.getStatus())
                          .build();

        return taskRepository.save(taskDB).toDTO();
    }

    @Override
    public TaskResponseDTO update(Long id, TaskRequestUpdateDTO requestUpdateDTO) {
        var taskDB = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado nenhuma tarefa com esse ID."));

        taskDB.setTitle(requestUpdateDTO.getTitle());
        taskDB.setStatus(requestUpdateDTO.getStatus());
        taskDB.setDescription(requestUpdateDTO.getDescription());
        taskDB.setUpdateDate(LocalDateTime.now());

        var taskUpdated = taskRepository.save(taskDB);

        return taskUpdated.toDTO();
    }

    @Override
    public TaskResponseDeleteDTO delete(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado nenhuma tarefa com esse ID."));
        return TaskResponseDeleteDTO.builder().message("Tarefa excluída com sucesso.").build();
    }
}
