package at.itkollegimst.Springboot.tutorial.error;

import at.itkollegimst.Springboot.tutorial.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.ErrorManager;

/**
 * Durch die @ControllerAdvice Notation wird dieser ExceptionHandler, immer wenn einer
 * unserer Controller eine Exception schmeißt, die jeweilige Exception handeln.
 * Zudem erstellt sie für die geworfene Exception eine Response und schickt diese als
 * Response Object zurück.
 * Wenn man möchte, könnte man auch Exceptions unterschiedlicher Controller in
 * unterschiedlichen ExceptionHandlers behandeln, was bei uns nicht notwendig ist.
 */
@ControllerAdvice //für die Klasse, die alle Exceptions handelt
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(DepartmentNotFoundException.class) //diese Methode wird benötigt, falls eine DepartmentNotFoundException geworfen wird
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception,
                                                                   WebRequest request) { //sollte man etwas aus dem request benötigen
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> employeeNotFoundException(EmployeeNotFoundException exception,
                                                                  WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}