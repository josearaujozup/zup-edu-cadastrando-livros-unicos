package br.com.zup.edu.livrosunicos.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	public boolean existsByIsbn(String isbn);
}
