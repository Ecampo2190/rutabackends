package com.ecampo.api.rutabackends.service;

import java.util.List;
import java.util.Optional;

import com.ecampo.api.rutabackends.Entity.EstadoProducto;
import com.ecampo.api.rutabackends.Entity.Producto;
import com.ecampo.api.rutabackends.dto.ProductoDTO;

public interface ProductoService {

    //Producto registarProducto(Producto producto);
    ProductoDTO registarProducto(Long categoriaId, ProductoDTO producto);

    List<ProductoDTO> listarProductos();

    Optional<ProductoDTO> buscarPorNombre(String nombre);

    Optional<ProductoDTO> buscarPorId(Long idProducto);
    
    ProductoDTO actualizarProducto(Long idProducto, ProductoDTO producto);

    void eliminarProducto(Long idProducto);

    ProductoDTO cambiarEstadoProducto(Long idProducto, EstadoProducto Newestado);

    List<ProductoDTO> obtenerProductosPorEstado(EstadoProducto estadoProducto);


}
