package com.ecampo.api.rutabackends.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecampo.api.rutabackends.Entity.Message;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private List<Message> mensajes= new ArrayList<>();

    public MessageController() {
        mensajes.add(new Message(1, "Hola mundo"));
        mensajes.add(new Message(2, "Hola desde Rutabackends"));
    }

    @GetMapping
    public List<Message> listarMensajes() {
        return mensajes;
    }

    @GetMapping("/{id}")
    public Message obtenerMessage(@PathVariable int id){
        Optional<Message> mensa= mensajes.stream().filter(m -> m.getId() == id).findFirst();
        return mensa.orElse(null);
    }

    @PostMapping
    public Message CrearMensaje(@RequestBody Message mensaje){
        mensajes.add(mensaje);
        return mensaje;
    }

    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable int id){
        mensajes.removeIf(x->x.getId()==id);
    }
}
