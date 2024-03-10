package br.senai.mainbooks.repository;

import br.senai.mainbooks.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long>
{

}
