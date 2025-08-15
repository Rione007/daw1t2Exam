package com.example.daw1_t2.api;

import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.entity.GeneroMusical;
import com.example.daw1_t2.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "*")
public class ArtistaRestController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<Artista>> listarArtistas() {
        try {
            List<Artista> artistas = artistaService.listarTodos();
            return ResponseEntity.ok(artistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> obtenerArtista(@PathVariable Integer id) {
        try {
            Artista artista = artistaService.obtenerPorId(id);
            if (artista != null) {
                return ResponseEntity.ok(artista);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Artista> crearArtista(@RequestBody Artista artista) {
        try {
            Artista guardado = artistaService.guardarYDevolver(artista);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> actualizarArtista(@PathVariable Integer id, @RequestBody Artista artista) {
        try {
            Artista existente = artistaService.obtenerPorId(id);
            if (existente == null) {
                return ResponseEntity.notFound().build();
            }
            artista.setId(id);
            Artista actualizado = artistaService.guardarYDevolver(artista);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable Integer id) {
        try {
            Artista artista = artistaService.obtenerPorId(id);
            if (artista == null) {
                return ResponseEntity.notFound().build();
            }
            artistaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/generos")
    public GeneroMusical[] listarGeneros() {
        return GeneroMusical.values();
    }
}
