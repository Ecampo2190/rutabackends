package com.ecampo.api.rutabackends.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecampo.api.rutabackends.Entity.Categoria;
import com.ecampo.api.rutabackends.dto.CategoriaDTO;

@Component
public class CategoriaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaDTO toDTO(Categoria categoria){
        return modelMapper.map(categoria,CategoriaDTO.class);
    }

    public Categoria toEntity(CategoriaDTO categoriaDTO){
        return modelMapper.map(categoriaDTO,Categoria.class);
    }
}
