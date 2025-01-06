package task.manager.api.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.manager.api.rest.dto.request.TaskRequestDTO;
import task.manager.api.rest.dto.request.TaskRequestUpdateDTO;
import task.manager.api.rest.dto.response.TaskResponseDTO;
import task.manager.api.rest.dto.response.TaskResponseDeleteDTO;
import task.manager.domain.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable(value = "id") Long id) {
        var taskDB = service.findById(id);
        return ResponseEntity.ok(taskDB);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        var taskSaved = service.create(taskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskSaved);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable(value = "id") Long id,
                                                  @Valid @RequestBody TaskRequestUpdateDTO updateDTO) {
        var taskUpdated = service.update(id, updateDTO);
        return ResponseEntity.ok(taskUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TaskResponseDeleteDTO> delete(@PathVariable(value = "id") Long id) {
        var taskDelete = service.delete(id);
        return ResponseEntity.ok(taskDelete);
    }

}
