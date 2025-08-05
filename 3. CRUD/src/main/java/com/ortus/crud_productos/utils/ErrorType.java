package com.ortus.crud_productos.utils;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    ENTITY_NOT_FOUND("Producto solicitado no encontrado", HttpStatus.NOT_FOUND),
    DUPLICATE_ENTITY("El producto ya existe", HttpStatus.CONFLICT),
    INTERNAL_ERROR("Error Interno del Servidor", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("Bad Request", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    ErrorType(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}