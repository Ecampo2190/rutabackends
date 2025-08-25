package com.ecampo.api.rutabackends.service;

import java.util.List;
import java.util.Optional;

import com.ecampo.api.rutabackends.Entity.Categoria;

public interface CategoriaService {

    Categoria crearCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    Optional<Categoria> obtenerCategoriaPorId(Long idCategoria);
    Categoria actualizarCategoria(Long idCategoria, Categoria categoria);
    void eliminarCategoria(Long idCategoria);

}
