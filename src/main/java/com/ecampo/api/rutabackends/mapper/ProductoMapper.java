package com.ecampo.api.rutabackends.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecampo.api.rutabackends.Entity.Producto;
import com.ecampo.api.rutabackends.dto.ProductoDTO;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Component
public class ProductoMapper {
    @Autowired
    private  ModelMapper modelMapper;

    public Producto convertirEntidad(ProductoDTO productoDTO){
        return modelMapper.map(productoDTO, Producto.class);
    }

    public ProductoDTO convertirDTO(Producto producto){
        return modelMapper.map(producto, ProductoDTO.class);
    }
}
