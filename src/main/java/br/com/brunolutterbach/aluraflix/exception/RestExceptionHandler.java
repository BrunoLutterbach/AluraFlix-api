package br.com.brunolutterbach.aluraflix.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails.builder()
                .title("Erro de recurso não encontrado")
                .date(ZonedDateTime.now())
                .status(404)
                .detail(e.getMessage())
                .build();
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.valueOf(resourceNotFoundDetails.getStatus()));
    }

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField).toList();
        return ValidationErrorDetails.builder()
                .title("Erro de validação")
                .date(ZonedDateTime.now())
                .status(400)
                .detail("Erro no campo " + errors + ". " + Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage())
                .build();
    }

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails.builder()
                .title("Erro")
                .date(ZonedDateTime.now())
                .status(404)
                .detail(e.getMessage())
                .build();
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.valueOf(resourceNotFoundDetails.getStatus()));
    }
}
