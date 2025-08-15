package com.example.daw1_t2.api;

import com.example.daw1_t2.dtos.ArtistaCreateDTO;
import com.example.daw1_t2.dtos.ArtistaDTO;
import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.entity.GeneroMusical;
import com.example.daw1_t2.service.ArtistaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "*")
public class ArtistaRestController {

    private final ArtistaService artistaService;

    public ArtistaRestController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> listarArtistas() {
        List<ArtistaDTO> artistas = artistaService.listarTodos()
                .stream()
                .map(a -> new ArtistaDTO(
                        a.getId(),
                        a.getNombre(),
                        a.getPaisOrigen(),
                        a.getGeneroMusical() != null ? a.getGeneroMusical().name() : null,
                        a.getFechaDebut() != null ? a.getFechaDebut().toString() : null
                ))
                .toList();
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> obtenerArtista(@PathVariable Integer id) {
        Artista a = artistaService.obtenerPorId(id);
        if (a == null) return ResponseEntity.notFound().build();

        ArtistaDTO dto = new ArtistaDTO(
                a.getId(),
                a.getNombre(),
                a.getPaisOrigen(),
                a.getGeneroMusical() != null ? a.getGeneroMusical().name() : null,
                a.getFechaDebut() != null ? a.getFechaDebut().toString() : null
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ArtistaDTO> crearArtista(@RequestBody ArtistaCreateDTO dto) {
        Artista artista = new Artista();
        artista.setNombre(dto.nombre());
        artista.setPaisOrigen(dto.paisOrigen());
        artista.setGeneroMusical(dto.generoMusical() != null ? GeneroMusical.valueOf(dto.generoMusical()) : null);
        if (dto.fechaDebut() != null && !dto.fechaDebut().isEmpty()) {
            artista.setFechaDebut(LocalDate.parse(dto.fechaDebut()));
        }

        Artista guardado = artistaService.guardarYDevolver(artista);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ArtistaDTO(
                        guardado.getId(),
                        guardado.getNombre(),
                        guardado.getPaisOrigen(),
                        guardado.getGeneroMusical() != null ? guardado.getGeneroMusical().name() : null,
                        guardado.getFechaDebut() != null ? guardado.getFechaDebut().toString() : null
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDTO> actualizarArtista(@PathVariable Integer id, @RequestBody ArtistaCreateDTO dto) {
        Artista existente = artistaService.obtenerPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();

        existente.setNombre(dto.nombre());
        existente.setPaisOrigen(dto.paisOrigen());
        existente.setGeneroMusical(dto.generoMusical() != null ? GeneroMusical.valueOf(dto.generoMusical()) : null);
        if (dto.fechaDebut() != null && !dto.fechaDebut().isEmpty()) {
            existente.setFechaDebut(LocalDate.parse(dto.fechaDebut()));
        }

        Artista actualizado = artistaService.guardarYDevolver(existente);

        return ResponseEntity.ok(
                new ArtistaDTO(
                        actualizado.getId(),
                        actualizado.getNombre(),
                        actualizado.getPaisOrigen(),
                        actualizado.getGeneroMusical() != null ? actualizado.getGeneroMusical().name() : null,
                        actualizado.getFechaDebut() != null ? actualizado.getFechaDebut().toString() : null
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable Integer id) {
        Artista artista = artistaService.obtenerPorId(id);
        if (artista == null) return ResponseEntity.notFound().build();
        artistaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/generos")
    public GeneroMusical[] listarGeneros() {
        return GeneroMusical.values();
    }
}
