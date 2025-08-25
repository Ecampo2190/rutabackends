package com.ecampo.api.rutabackends.service;

import java.util.List;
import java.util.Optional;

import com.ecampo.api.rutabackends.Entity.EstadoProducto;
import com.ecampo.api.rutabackends.Entity.Producto;

public interface ProductoService {

    Producto registarProducto(Producto producto);

    List<Producto> listarProductos();

    Optional<Producto> buscarPorNombre(String nombre);

    Optional<Producto> buscarPorId(Long idProducto);
    
    Producto actualizarProducto(Long idProducto, Producto producto);

    void eliminarProducto(Long idProducto);

    Producto cambiarEstadoProducto(Long idProducto, EstadoProducto Newestado);

    List<Producto> obtenerProductosPorEstado(EstadoProducto estadoProducto);

    List<Producto> obtenerProductosPorCategoria(Long idCategoria);

}
