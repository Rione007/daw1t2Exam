package com.example.daw1_t2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    @GetMapping("/")
    public String homePage() {
        return "index"; // Página principal del dashboard
    }

    @GetMapping("/artistas-view")
    public String artistasPage() {
        return "artistas";
    }

    @GetMapping("/canciones-view")
    public String cancionesPage() {
        return "canciones";
    }

    @GetMapping("/reportes-view")
    public String reportesPage() {
        return "reportes";
    }
}
