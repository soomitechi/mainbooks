package br.senai.mainbooks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Livro
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 15)
    private String codigo;

    @NotBlank
    @Size(max = 50)
    private String titulo;

    @NotBlank
    @Size(min = 3, max = 70)
    private String autor;

    @NotBlank
    @Size(min = 10, max = 13)
    private String isbn;

    @NotBlank
    @Size(max = 50)
    private String editora;
}
