package com.ecampo.api.rutabackends.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
Controller: Es una clase de java que gestiona las peticiones HTTP como (GET, POST, PUT, DELETE) y devuelve respuestas.
Controlador Rest: Es una clase en java que expone un conjunto de endpoints HTTP(URL) para interactuar con la aplicación.
    * Indica que esta clase es un controlador REST
    * Combina @Controller y @ResponseBody
    * no maneja vistas HTML
 */
@RestController// indicammos que esta clase es un controlador web en APIS Rest
@RequestMapping("/micontroller")//configuramos una URL para todos los metodos del controlador
public class SaludoController {

    @GetMapping
    public String saludar(){
        return "Hola mundo";
    }
}
