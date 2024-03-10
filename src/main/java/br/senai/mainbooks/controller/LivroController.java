package br.senai.mainbooks.controller;

import br.senai.mainbooks.exception.LivroNaoEncontrado;
import br.senai.mainbooks.model.Livro;
import br.senai.mainbooks.service.LivroService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class LivroController
{
    @Autowired
    private LivroService service;

    @GetMapping("/")
    public String HomePage()
    {
        return "homepage";
    }

    @GetMapping("/adicionar")
    public String AdicionarLivro()
    {
        return "adicionar-livro";
    }

    @GetMapping("/livros")
    public String BuscarLivros(@RequestParam(value = "mensagem", required = false) String mensagem, Model model)
    {
        List<Livro> livros =  service.BuscarLivros();
        model.addAttribute("livros", livros);
        model.addAttribute("mensagem", mensagem);

        return "listar-livros";
    }

    @GetMapping("/editar")
    public String ExibirFormularioEdicao(Model model, RedirectAttributes redirectAttributes, @RequestParam Long id)
    {
        String destino;

        try
        {
            Livro livro = service.BuscarLivro(id);
            model.addAttribute("livro", livro);
            destino = "editar-livro";
        }
        catch (LivroNaoEncontrado erro)
        {
            erro.printStackTrace();
            redirectAttributes.addAttribute("mensagem", erro.getMessage());
            destino = "redirect:livros";
        }

        return destino;
    }

    @GetMapping("/excluir")
    public String RemoverLivro(@RequestParam Long id, RedirectAttributes redirectAttributes)
    {
        try
        {
            service.ExcluirLivro(id);
            redirectAttributes.addAttribute("mensagem", "O livro com id #" + id + " foi excluído.");
        }
        catch (LivroNaoEncontrado erro)
        {
            erro.printStackTrace();
            redirectAttributes.addAttribute("mensagem", erro.getMessage());
        }

        return "redirect:livros";
    }

    @PostMapping("salvar")
    public String SalvarLivro(@ModelAttribute Livro livro, Model model)
    {
        try
        {
            long id = service.SalvarLivro(livro).getId();
            String mensagem = "Livro com id #" + id + " foi salvo.";
            model.addAttribute("mensagem", mensagem);
        }
        catch (ConstraintViolationException erro)
        {
            erro.printStackTrace();
            String mensagem = "Campos inválidos. Tente novamente.";
            model.addAttribute("mensagem", mensagem);
        }

        return "adicionar-livro";
    }

    @PostMapping("/atualizar")
    public String AtualizarLivro(@ModelAttribute Livro livro, RedirectAttributes redirectAttributes)
    {
        String destino;

        try
        {
            service.AtualizarLivro(livro);
            long id = livro.getId();
            redirectAttributes.addAttribute("mensagem", "Informações do livro com id #" + id + " atualizadas.");
            destino = "redirect:livros";
        }
        catch (TransactionSystemException erro)
        {
            erro.printStackTrace();
            redirectAttributes.addAttribute("id", livro.getId());
            redirectAttributes.addAttribute("mensagem", "Modificações inválidas. Tente novamente.");
            destino = "redirect:editar";
        }

        return destino;
    }
}
