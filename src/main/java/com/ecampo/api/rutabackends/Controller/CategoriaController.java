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
import org.springframework.web.bind.annotation.RestController;

import com.ecampo.api.rutabackends.Entity.Categoria;
import com.ecampo.api.rutabackends.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
       Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
       return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    public ResponseEntity<?> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long idCategoria){
        Optional<Categoria> categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
        if(categoria.isPresent()) {
            return new ResponseEntity<>(categoria.get(), HttpStatus.OK);
        }else{
            throw new RuntimeException("Categoría no encontrada");
        }
    }

    @PutMapping("{idCategoria}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long idCategoria, @RequestBody Categoria categoria) {
        try{
            Categoria categoriaActualizada = categoriaService.actualizarCategoria(idCategoria, categoria);
            if(categoriaActualizada != null) {
                return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
            }else{
                throw new RuntimeException("Categoría no encontrada");
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long idCategoria) {
        try {
            categoriaService.eliminarCategoria(idCategoria);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
