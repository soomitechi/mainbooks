package br.senai.mainbooks.exception;

public class LivroNaoEncontrado extends RuntimeException
{
    public LivroNaoEncontrado()
    {
        super();
    }

    public  LivroNaoEncontrado(String mensagem)
    {
        super(mensagem);
    }
}
