package com.ecampo.api.rutabackends.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecampo.api.rutabackends.Entity.EstadoProducto;
import com.ecampo.api.rutabackends.Entity.Producto;
import com.ecampo.api.rutabackends.repository.ProductoRepository;
import com.ecampo.api.rutabackends.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto registarProducto(Producto producto) {
        
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        
        return productoRepository.findAll();

    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        
        Optional<Producto> producto = productoRepository.findByNombreProducto(nombre);
        return producto.isPresent() ? producto : Optional.empty();
    }

    @Override
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        
        Producto productoExistente = productoRepository
                                    .findByIdProducto(idProducto)
                                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setEstadoProducto(producto.getEstadoProducto());

        return productoRepository.save(productoExistente);
         
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        productoRepository
                          .findByIdProducto(idProducto)
                          .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));

        productoRepository.deleteById(idProducto);
    }

    @Override
    public Producto cambiarEstadoProducto(Long idProducto, EstadoProducto Newestado) {
        Producto productoExistente = productoRepository
                                    .findByIdProducto(idProducto)
                                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));
        
        productoExistente.setEstadoProducto(Newestado);
        return productoRepository.save(productoExistente);
    }

    @Override
    public List<Producto> obtenerProductosPorEstado(EstadoProducto estadoProducto) {
       
        return productoRepository.findByEstadoProducto(estadoProducto);
    }

    @Override
    public Optional<Producto> buscarPorId(Long idProducto) {
        Optional<Producto> producto = productoRepository.findByIdProducto(idProducto);
        return producto.isPresent() ? producto : Optional.empty();
    }


}
