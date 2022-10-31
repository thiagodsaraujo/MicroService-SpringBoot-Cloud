package com.github.thg.bankapi.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.thg.bankapi.services.exceptions.ObjectNotFoundException;
import com.github.thg.bankapi.services.exceptions.SaldoInsuficienteException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<StandardError> saldoInsuficienteException(SaldoInsuficienteException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Operacao Invalida: Saldo insuficiente");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NO_CONTENT;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Erro: Id não cadastrado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
//
//    @ExceptionHandler(ClientNotFoundException.class)
//    public ResponseEntity<StandardError> clientNotFoundException(ClientNotFoundException e, HttpServletRequest request){
//        HttpStatus status = HttpStatus.NO_CONTENT;
//        StandardError err = new StandardError();
//        err.setTimestamp(Instant.now());
//        err.setStatus(status.value());
//        err.setError("Erro: Cliente não cadastrado com Id informado");
//        err.setMessage(e.getMessage());
//        err.setPath(request.getRequestURI());
//        return ResponseEntity.status(status).body(err);
//    }
}
