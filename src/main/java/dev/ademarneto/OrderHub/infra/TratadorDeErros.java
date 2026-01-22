package dev.ademarneto.OrderHub.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {

        List<FieldError> erros = ex.getFieldErrors();

        List<DadosErroValidacao> listaDeErrosTratada = erros.stream()
                .map(erro -> new DadosErroValidacao(erro))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(listaDeErrosTratada);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
