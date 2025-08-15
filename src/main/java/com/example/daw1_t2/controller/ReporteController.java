package com.example.daw1_t2.controller;

import com.example.daw1_t2.dtos.TopArtistaDTO;
import com.example.daw1_t2.service.ReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public String mostrarReportes(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer limite,
            Model model
    ) {
        // Parte A
        if (genero != null && !genero.isBlank()) {
            Map<String, Object> resultado = reporteService.cancionesPorGenero(genero);
            model.addAttribute("cancionesGenero", resultado.get("canciones"));
            model.addAttribute("totalGenero", resultado.get("total_reproducciones"));
            model.addAttribute("generoSeleccionado", genero);
        }

        // Parte C
        if (limite != null && limite > 0) {
            model.addAttribute("topArtistas", reporteService.topArtistas(limite));
            model.addAttribute("limiteSeleccionado", limite);
        }

        return "reportes"; // Un solo HTML para todo
    }
}
