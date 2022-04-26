package br.com.zup.edu.livrosunicos.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.livrosunicos.model.Livro;
import br.com.zup.edu.livrosunicos.model.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	private final LivroRepository repository;
	
	public LivroController(LivroRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroDTO request, UriComponentsBuilder uriComponentsBuilder){
		
		if(repository.existsByIsbn(request.getIsbn())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Livro já existe no sistema");
		}
		
        Livro livro = request.toModel();

        repository.save(livro);

        URI location = uriComponentsBuilder.path("/livros/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
		Map<String, Object> body = Map.of(
				"message", "livro já existente no sistema",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}
}
