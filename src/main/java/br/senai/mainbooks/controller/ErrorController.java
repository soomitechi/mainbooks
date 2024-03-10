package br.senai.mainbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Validated
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController
{
    @GetMapping("/error")
    public String ExibirPaginaError()
    {
        return "error-page";
    }
}
