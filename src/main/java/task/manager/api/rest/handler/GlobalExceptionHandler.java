package task.manager.api.rest.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import task.manager.api.rest.dto.response.CustomErrorResponse;
import task.manager.domain.exception.TaskNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleTaskNotFoundException(TaskNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                                                               .timestamp(LocalDateTime.now())
                                                               .status(status.value())
                                                               .error(status.getReasonPhrase())
                                                               .message(ex.getMessage())
                                                               .path(request.getRequestURI())
                                                               .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

}
