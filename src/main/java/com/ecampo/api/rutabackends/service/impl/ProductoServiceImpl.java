package com.ecampo.api.rutabackends.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecampo.api.rutabackends.Entity.Categoria;
import com.ecampo.api.rutabackends.Entity.EstadoProducto;
import com.ecampo.api.rutabackends.Entity.Producto;
import com.ecampo.api.rutabackends.dto.ProductoDTO;
import com.ecampo.api.rutabackends.excepcions.BadRequestException;
import com.ecampo.api.rutabackends.excepcions.ResourceNotFoundException;
import com.ecampo.api.rutabackends.mapper.ProductoMapper;
import com.ecampo.api.rutabackends.repository.CategoriaRepository;
import com.ecampo.api.rutabackends.repository.ProductoRepository;
import com.ecampo.api.rutabackends.service.ProductoService;

import lombok.SneakyThrows;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoMapper productoMapper;
    
    @Override
    public List<ProductoDTO> listarProductos() {
        
         List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(productoMapper::convertirDTO)
                .toList();

    }

    @Override
    public Optional<ProductoDTO> buscarPorNombre(String nombre) {
        
         Optional<Producto> producto = productoRepository.findByNombreProducto(nombre);
        return producto.map(productoMapper::convertirDTO);
    }

    @Override
    public ProductoDTO actualizarProducto(Long idProducto, ProductoDTO productoDTO) {
        
       Producto productoExistente = productoRepository.findByIdProducto(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID " + idProducto + " no encontrado"));

        productoExistente.setNombreProducto(productoDTO.getNombreProducto());
        productoExistente.setDescripcion(productoDTO.getDescripcionProducto());
        productoExistente.setPrecio(productoDTO.getPrecioProducto());
        productoExistente.setCantidad(productoDTO.getCantidad());
        productoExistente.setEstadoProducto(productoDTO.getEstadoProducto());

        if(productoDTO.getCategoria() != null && productoDTO.getCategoria().getIdCategoria() != null){
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
            productoExistente.setCategoria(categoria);
        }

        Producto productoActualizado = productoRepository.save(productoExistente);
        return productoMapper.convertirDTO(productoActualizado);
         
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        productoRepository
                          .findByIdProducto(idProducto)
                          .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + idProducto));

        productoRepository.deleteById(idProducto);
    }

    @Override
    public ProductoDTO cambiarEstadoProducto(Long idProducto, EstadoProducto Newestado) {
        Producto productoExistente = productoRepository
                                    .findByIdProducto(idProducto)
                                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + idProducto));
        
        productoExistente.setEstadoProducto(Newestado);
        Producto productoActualizado = productoRepository.save(productoExistente);
        return productoMapper.convertirDTO(productoActualizado);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorEstado(EstadoProducto estadoProducto) {
       
        List<Producto> productos = productoRepository.findByEstadoProducto(estadoProducto);
        return productos.stream()
                .map(productoMapper::convertirDTO)
                .toList();
    }

    @Override
    public Optional<ProductoDTO> buscarPorId(Long idProducto) {
        Optional<Producto> producto = productoRepository.findByIdProducto(idProducto);
        return producto.map(productoMapper::convertirDTO);
    }

    @Override
    public ProductoDTO registarProducto(Long categoriaId, ProductoDTO productoDTO) {
       Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría con ID " + categoriaId + " no encontrada"));

        if(productoDTO.getPrecioProducto() == null || productoDTO.getPrecioProducto() <= 0){
            throw new BadRequestException("El precio del producto debe ser mayor que 0");
        }

        Producto producto = productoMapper.convertirEntidad(productoDTO);
        producto.setCategoria(categoria);

        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.convertirDTO(productoGuardado);
    }




}
