package br.com.zup.edu.livrosunicos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String isbn;
	
	@Column(nullable = false)
    private LocalDate dataPublicacao;

	public Livro(String titulo, String isbn, LocalDate dataPublicacao) {
		this.titulo = titulo;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
	}

	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Livro() {
	}

	public Long getId() {
		return id;
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
	
}
