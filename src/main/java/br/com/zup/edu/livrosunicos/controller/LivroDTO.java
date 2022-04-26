package br.com.zup.edu.livrosunicos.controller;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.livrosunicos.model.Livro;

public class LivroDTO {
	@NotBlank
    private String titulo;

	@ISBN(type = ISBN.Type.ANY)
    private String isbn;

	@Past
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;

	public LivroDTO(@NotBlank String titulo, @ISBN(type = Type.ANY) String isbn,
			@Past @NotNull LocalDate dataPublicacao) {
		this.titulo = titulo;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
	}
	
	public LivroDTO() {
		
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public Livro toModel() {
		return new Livro(titulo, isbn, dataPublicacao);
	}
	
	
	
}
