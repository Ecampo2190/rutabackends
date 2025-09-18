package com.ecampo.api.rutabackends.Controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecampo.api.rutabackends.Entity.EstadoProducto;
import com.ecampo.api.rutabackends.Entity.Producto;
import com.ecampo.api.rutabackends.dto.ProductoDTO;
import com.ecampo.api.rutabackends.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /*
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.registarProducto((long) 1, producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }
    */

    @PostMapping("/registrarProducto/{categoriaId}")
    public ResponseEntity<?> registrarProducto2(
            @PathVariable Long categoriaId,
            @RequestParam("nombreProducto") String nombreProducto,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("estadoProducto") EstadoProducto estadoProducto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombreProducto(nombreProducto);
        productoDTO.setDescripcionProducto(descripcion);
        productoDTO.setPrecioProducto(precio);
        productoDTO.setCantidad(cantidad);
        productoDTO.setEstadoProducto(estadoProducto);

        ProductoDTO productoBBDD = productoService.registarProducto(categoriaId, productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoBBDD);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        Optional<ProductoDTO> productoDTO = productoService.buscarPorNombre(nombre);
        return productoDTO.isPresent() ? ResponseEntity.ok(productoDTO.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @GetMapping("/buscar/id/{idProducto}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idProducto) {
        Optional<ProductoDTO> productoDTO = productoService.buscarPorId(idProducto);
        return productoDTO.isPresent() ? ResponseEntity.ok(productoDTO.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto) {
        try {
            ProductoDTO productoDTO = new ProductoDTO();

            productoDTO.setNombreProducto(producto.getNombreProducto());
            productoDTO.setDescripcionProducto(producto.getDescripcion());
            productoDTO.setPrecioProducto(producto.getPrecio());
            productoDTO.setCantidad(producto.getCantidad());
            productoDTO.setEstadoProducto(producto.getEstadoProducto());

            ProductoDTO productoActualizado = productoService.actualizarProducto(idProducto, productoDTO);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PutMapping("/actualizarProducto/{idProducto}")
    public ResponseEntity<?> actualizarProducto2(
            @PathVariable Long idProducto,
            @RequestParam("nombreProducto") String nombreProducto,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("estadoProducto") EstadoProducto estadoProducto) {
        try {
            ProductoDTO productoDTO = new ProductoDTO();

            productoDTO.setNombreProducto(nombreProducto);
            productoDTO.setDescripcionProducto(descripcion);
            productoDTO.setPrecioProducto(precio);
            productoDTO.setCantidad(cantidad);
            productoDTO.setEstadoProducto(estadoProducto);

            ProductoDTO productoBBDD = productoService.actualizarProducto(idProducto, productoDTO);
            return ResponseEntity.ok(productoBBDD);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto) {
        try {
            productoService.eliminarProducto(idProducto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/estado/{idProducto}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long idProducto,
            @RequestBody EstadoProducto estadoProducto) {
        try {
            ProductoDTO productoActualizado = productoService.cambiarEstadoProducto(idProducto, estadoProducto);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProductoDTO>> listarPorEstado(@PathVariable EstadoProducto estado) {

        List<ProductoDTO> productoDTOS = productoService.obtenerProductosPorEstado(estado);
        return ResponseEntity.ok(productoDTOS);
    }

    /*
     * crear un metodo que permita ingresar varios json y cree todos los productos
     * ingresados
     */
    /*
     * un metodo donde debo ingresar la cantidad de productos a crear y si o si se
     * debe pasar esa cantidad
     * o generara error
     */

}
