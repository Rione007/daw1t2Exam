package com.example.daw1_t2.controller;

import com.example.daw1_t2.entity.Cancion;
import com.example.daw1_t2.service.ArtistaService;
import com.example.daw1_t2.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/canciones")
public class CancionController {

    @Autowired
    private CancionService cancionService;

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public String listarCanciones(Model model) {
        List<Cancion> canciones = cancionService.listarTodas();
        model.addAttribute("canciones", canciones);
        return "canciones/lista"; // vista: templates/canciones/lista.html
    }

    @GetMapping("/{id}")
    public String obtenerCancion(@PathVariable Integer id, Model model) {
        Cancion cancion = cancionService.obtenerPorId(id);
        model.addAttribute("cancion", cancion);
        return "canciones/detalle";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("cancion", new Cancion());
        model.addAttribute("artistas", artistaService.listarTodos());
        return "canciones/formulario";
    }

    @PostMapping
    public String crearCancion(@ModelAttribute Cancion cancion) {
        cancionService.guardar(cancion);
        return "redirect:/canciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCancion(@PathVariable Integer id) {
        cancionService.eliminar(id);
        return "redirect:/canciones";
    }
}
