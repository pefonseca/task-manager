package task.manager.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.manager.api.rest.dto.request.TaskRequestDTO;
import task.manager.api.rest.dto.request.TaskRequestUpdateDTO;
import task.manager.api.rest.dto.response.TaskResponseDTO;
import task.manager.api.rest.dto.response.TaskResponseDeleteDTO;
import task.manager.domain.entity.Task;
import task.manager.domain.exception.TaskNotFoundException;
import task.manager.domain.service.TaskService;
import task.manager.infra.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

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
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Não foi encontrado nenhuma tarefa com esse ID.")).toDTO();
    }

    @Override
    public TaskResponseDTO create(TaskRequestDTO requestDTO) {
        Task taskDB = Task.builder()
                          .title(requestDTO.getTitle())
                          .description(requestDTO.getDescription())
                          .status(requestDTO.getStatus())
                          .dueDate(LocalDateTime.now())
                          .build();

        return taskRepository.save(taskDB).toDTO();
    }

    @Override
    public TaskResponseDTO update(Long id, TaskRequestUpdateDTO requestUpdateDTO) {
        TaskResponseDTO taskDB = findById(id);

        var taskSaved = taskDB.toUpdate(requestUpdateDTO);

        var taskUpdated = taskRepository.save(taskSaved);

        return taskUpdated.toDTO();
    }

    @Override
    public TaskResponseDeleteDTO delete(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Não foi encontrado nenhuma tarefa com esse ID."));
        taskRepository.deleteById(id);
        return TaskResponseDeleteDTO.builder().message("Tarefa excluída com sucesso.").build();
    }
}