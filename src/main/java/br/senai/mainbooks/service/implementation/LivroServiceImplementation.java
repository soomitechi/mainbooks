package br.senai.mainbooks.service.implementation;

import br.senai.mainbooks.exception.LivroNaoEncontrado;
import br.senai.mainbooks.model.Livro;
import br.senai.mainbooks.repository.LivroRepository;
import br.senai.mainbooks.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImplementation implements LivroService
{
    @Autowired
    private LivroRepository repository;

    @Override
    public Livro SalvarLivro(Livro livro)
    {
        return repository.save(livro);
    }

    @Override
    public List<Livro> BuscarLivros()
    {
        return repository.findAll();
    }

    @Override
    public Livro BuscarLivro(long id)
    {
        Optional<Livro> livro = repository.findById(id);
        if (livro.isPresent() == true)
        {
            return livro.get();
        }
        else
        {
            throw new LivroNaoEncontrado("Livro não encontrado para id #" + id);
        }
    }

    @Override
    public void ExcluirLivro(long id)
    {
        repository.deleteById(id);
    }

    @Override
    public void AtualizarLivro(Livro livro)
    {
        repository.save(livro);
    }
}
