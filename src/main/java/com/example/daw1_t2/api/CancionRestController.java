package com.example.daw1_t2.api;

import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.entity.Cancion;
import com.example.daw1_t2.service.ArtistaService;
import com.example.daw1_t2.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
@CrossOrigin(origins = "*")
public class CancionRestController {

    @Autowired
    private CancionService cancionService;

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<Cancion>> listarCanciones() {
        try {
            List<Cancion> canciones = cancionService.listarTodas();
            return ResponseEntity.ok(canciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> obtenerCancion(@PathVariable Integer id) {
        try {
            Cancion cancion = cancionService.obtenerPorId(id);
            if (cancion != null) {
                return ResponseEntity.ok(cancion);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Cancion> crearCancion(@RequestBody Cancion cancion) {
        try {

            Integer idArtista = cancion.getArtista().getId();
            Artista artista = artistaService.obtenerPorId(idArtista);
            if (artista == null) {
                return ResponseEntity.badRequest().build();
            }
            cancion.setArtista(artista);
            Cancion guardada = cancionService.guardarYDevolver(cancion);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable Integer id) {
        try {
            Cancion cancion = cancionService.obtenerPorId(id);
            if (cancion == null) {
                return ResponseEntity.notFound().build();
            }
            cancionService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
