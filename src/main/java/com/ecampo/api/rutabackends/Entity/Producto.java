package com.ecampo.api.rutabackends.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    @Column(name="nombre_producto", nullable = false, length=100)
    private String nombreProducto;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio", nullable = false)
    private Double precio;
    @Column(name="cantidad", nullable = false)
    private int cantidad;
    @Enumerated(EnumType.STRING)
    @Column(name="estado_producto", nullable = false) 
    private EstadoProducto estadoProducto;

}
