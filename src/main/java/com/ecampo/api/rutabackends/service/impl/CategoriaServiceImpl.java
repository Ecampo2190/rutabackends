package com.ecampo.api.rutabackends.service.impl;

import com.ecampo.api.rutabackends.Entity.Categoria;
import com.ecampo.api.rutabackends.dto.CategoriaDTO;
import com.ecampo.api.rutabackends.excepcions.BadRequestException;
import com.ecampo.api.rutabackends.excepcions.ResourceNotFoundException;
import com.ecampo.api.rutabackends.mapper.CategoriaMapper;
import com.ecampo.api.rutabackends.repository.CategoriaRepository;
import com.ecampo.api.rutabackends.service.CategoriaService;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private CategoriaMapper categoriaMapper;
//comentario git
    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {

        if(categoriaRepository.existsByNombreCategoria(categoriaDTO.getNombreCategoria())){ 
            throw new BadRequestException("La categoría ya existe");
        }

        Categoria categoria= categoriaMapper.toEntity(categoriaDTO);
        Categoria nuevaCategoria= categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(nuevaCategoria);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                    .map(categoriaMapper::toDTO)
                    .collect(Collectors.toList());

    }

    @Override
    public Optional<CategoriaDTO> obtenerCategoriaPorId(Long idCategoria) {
        Optional<Categoria> categoria=categoriaRepository.findById(idCategoria);
        return categoria.map(categoriaMapper::toDTO);

    }

    @Override
    public CategoriaDTO actualizarCategoria(Long idCategoria, CategoriaDTO categoriaDTO) {
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        categoriaExistente.setNombreCategoria(categoriaDTO.getNombreCategoria());
        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);
        return categoriaMapper.toDTO(categoriaActualizada);
        
    }


    @Override
    public void eliminarCategoria(Long idCategoria) {
        
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);
        if(categoriaExistente.isPresent()){
            categoriaRepository.delete(categoriaExistente.get());
        }else{
            throw new ResourceNotFoundException("Categoría no encontrada");
        }

    }

}
