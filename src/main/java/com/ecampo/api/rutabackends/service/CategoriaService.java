package com.ecampo.api.rutabackends.service;

import java.util.List;
import java.util.Optional;
import com.ecampo.api.rutabackends.dto.CategoriaDTO;
import com.ecampo.api.rutabackends.excepcions.BadRequestException;

public interface CategoriaService {

    CategoriaDTO crearCategoria(CategoriaDTO categoria)throws BadRequestException;
    List<CategoriaDTO> listarCategorias();
    Optional<CategoriaDTO> obtenerCategoriaPorId(Long idCategoria);
    CategoriaDTO actualizarCategoria(Long idCategoria, CategoriaDTO categoria);
    void eliminarCategoria(Long idCategoria);

}
