package br.com.biblioteca.anandamoyi.infra.web.exception;

import br.com.biblioteca.anandamoyi.domain.exception.ExemplarInDisponivelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Livro não encontrado
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", 404,
                        "error", ex.getMessage()
                )
        );
    }

    // Regra de negócio (todos os livros da mesma edição está emprestado)
    @ExceptionHandler(ExemplarInDisponivelException.class)
    public ResponseEntity<?> handleExemplarIndisponivel(
            ExemplarInDisponivelException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 🔥 409
                .body(Map.of(
                        "status", 409,
                        "timestamp", LocalDateTime.now(),
                        "error", ex.getMessage()
                ));
    }

    // Regra de negócio (devolver sem emprestar)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalState(IllegalStateException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", 409,
                        "error", ex.getMessage()
                )
        );
    }
}
