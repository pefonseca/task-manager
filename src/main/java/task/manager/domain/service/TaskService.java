package task.manager.domain.service;

import task.manager.api.rest.dto.request.TaskRequestDTO;
import task.manager.api.rest.dto.request.TaskRequestUpdateDTO;
import task.manager.api.rest.dto.response.TaskResponseDTO;
import task.manager.api.rest.dto.response.TaskResponseDeleteDTO;

import java.util.List;

public interface TaskService {

    List<TaskResponseDTO> findAll();
    TaskResponseDTO findById(Long id);
    TaskResponseDTO create(TaskRequestDTO requestDTO);
    TaskResponseDTO update(Long id, TaskRequestUpdateDTO requestUpdateDTO);
    TaskResponseDeleteDTO delete(Long id);

}
