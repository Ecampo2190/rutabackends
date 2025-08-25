package com.ecampo.api.rutabackends.service.impl;

import com.ecampo.api.rutabackends.Entity.Categoria;
import com.ecampo.api.rutabackends.repository.CategoriaRepository;
import com.ecampo.api.rutabackends.service.CategoriaService;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaRepository categoriaRepository;
//comentario git
    @Override
    @SneakyThrows
    public Categoria crearCategoria(Categoria categoria) {
        if(categoriaRepository.existsByNombreCategoria(categoria.getNombreCategoria())){
            
            throw new BadRequestException("La categoría ya existe");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
       return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long idCategoria) {
       return categoriaRepository.findById(idCategoria);
    }

    @Override
    public Categoria actualizarCategoria(Long idCategoria, Categoria categoria) {
        Categoria categoriaExistente=null;
        try {
            categoriaExistente = categoriaRepository.findById(idCategoria)
                                            .orElseThrow(()-> new Exception("Categoría no encontrada"));
            categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoriaRepository.save(categoriaExistente);

        
    }


    @Override
    @SneakyThrows
    public void eliminarCategoria(Long idCategoria) {
        
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);
        if(categoriaExistente.isPresent()){
            categoriaRepository.delete(categoriaExistente.get());
        }else{
            throw new BadRequestException("Categoría no encontrada");
        }

    }

}
