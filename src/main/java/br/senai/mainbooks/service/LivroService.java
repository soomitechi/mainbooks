package br.senai.mainbooks.service;

import br.senai.mainbooks.model.Livro;

import java.util.List;

public interface LivroService
{
    public Livro SalvarLivro(Livro livro);

    public List<Livro> BuscarLivros();

    public Livro BuscarLivro(long id);

    public void ExcluirLivro(long id);

    public void AtualizarLivro(Livro livro);
}
