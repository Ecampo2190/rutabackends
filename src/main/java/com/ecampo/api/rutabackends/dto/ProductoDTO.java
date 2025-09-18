package com.ecampo.api.rutabackends.dto;

import com.ecampo.api.rutabackends.Entity.Categoria;
import com.ecampo.api.rutabackends.Entity.EstadoProducto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long idProducto;
    
    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(max = 100, message = "El nombre del producto no puede tener mas de 100 caracteres")
    private String nombreProducto;
    @Size(max = 255, message = "La descripcion del producto no puede tener mas de 255 caracteres")
    private String descripcionProducto;
    @NotNull(message = "El precio del producto no puede estar vacio")
    @Min(value = 0, message = "El precio del producto no puede ser negativo")
    private Double precioProducto;
    @NotNull(message = "La cantidad del producto no puede estar vacio")
    @Min(value = 1, message = "La cantidad del producto debe ser al menos 1")
    private int cantidad;
    @NotNull(message = "El estado del producto no puede estar vacio")
    private EstadoProducto estadoProducto;
    private Categoria categoria;
}
