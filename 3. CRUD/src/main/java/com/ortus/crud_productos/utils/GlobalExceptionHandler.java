package com.ortus.crud_productos.utils;

import com.ortus.crud_productos.models.dtos.MessageDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MessageDTO> handleCustomException(CustomException ex) {
        String message = ex.getErrorType().getMessage() + ": " + ex.getEntityName();
        return new ResponseEntity<>(new MessageDTO(message), ex.getErrorType().getStatus());
    }

    // ❗️ Validaciones de @Valid en @RequestBody (DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String details = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(new MessageDTO(details)); // 400
    }

    // ❗️ Validaciones en parámetros (@RequestParam, @PathVariable) con @Validated
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageDTO> handleConstraintViolation(ConstraintViolationException ex) {
        String details = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(new MessageDTO(details)); // 400
    }

    // ❗️ JSON malformado / tipos inválidos en el body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageDTO> handleNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new MessageDTO("Cuerpo de la solicitud inválido o malformado"));
    }

    // ❗️ Tipos inválidos en query/path (?id=abc cuando se espera Long)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<MessageDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new MessageDTO("Parámetro inválido: " + ex.getName()));
    }

    // Opcional: errores de negocio simples que lances como IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageDTO> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(ex.getMessage()));
    }

    // ⚠️ Dejá el genérico al final
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(new MessageDTO("Internal Server Error"));
    }
}