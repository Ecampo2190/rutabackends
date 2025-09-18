package com.ecampo.api.rutabackends.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Long idCategoria;
    @NotBlank(message = "El nombre de la categoria no puede estar vacio")
    @Size(min=3, max = 50, message = "El nombre de la categoria no puede tener mas de 50 caracteres")
    private String nombreCategoria;
}
