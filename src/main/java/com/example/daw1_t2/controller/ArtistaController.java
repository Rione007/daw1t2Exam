package com.example.daw1_t2.controller;

import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.entity.GeneroMusical;
import com.example.daw1_t2.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public String listarArtistas(Model model) {
        List<Artista> artistas = artistaService.listarTodos();
        model.addAttribute("artistas", artistas);
        return "artistas/lista"; // vista: templates/artistas/lista.html
    }

    @GetMapping("/{id}")
    public String obtenerArtista(@PathVariable Integer id, Model model) {
        Artista artista = artistaService.obtenerPorId(id);
        model.addAttribute("artista", artista);
        return "artistas/detalle"; // vista: templates/artistas/detalle.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("artista", new Artista());
        model.addAttribute("generos", GeneroMusical.values());
        return "artistas/formulario"; // vista: templates/artistas/formulario.html
    }

    @PostMapping
    public String crearArtista(@ModelAttribute Artista artista) {
        artistaService.guardar(artista);
        return "redirect:/artistas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Artista artista = artistaService.obtenerPorId(id);
        model.addAttribute("artista", artista);
        model.addAttribute("generos", GeneroMusical.values());
        return "artistas/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarArtista(@ModelAttribute Artista artista) {
        artistaService.guardar(artista);
        return "redirect:/artistas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarArtista(@PathVariable Integer id) {
        artistaService.eliminar(id);
        return "redirect:/artistas";
    }
}