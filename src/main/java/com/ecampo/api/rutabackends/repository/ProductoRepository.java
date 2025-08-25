package com.ecampo.api.rutabackends.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

import com.ecampo.api.rutabackends.Entity.Producto;
import com.ecampo.api.rutabackends.Entity.EstadoProducto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    Optional<Producto> findByNombreProducto(String nombreProducto);
    Optional<Producto> findByIdProducto(Long idProducto);
    List<Producto> findByEstadoProducto(EstadoProducto estadoProducto);
}
