package com.example.encuestas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/encuestas")
@CrossOrigin(origins = "*")
public class EncuestaController {

    @Autowired
    private EncuestaRepository encuestaRepository;

    // GET /api/encuestas -> devuelve todas las encuestas
    @GetMapping
    public List<Encuesta> listar() {
        return encuestaRepository.findAll();
    }

    // GET /api/encuestas/5 -> devuelve una encuesta específica por su id
    @GetMapping("/{id}")
    public Encuesta obtener(@PathVariable Long id) {
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        return encuesta.orElse(null);
    }

    // POST /api/encuestas -> crea una nueva encuesta
    @PostMapping
    public Encuesta crear(@RequestBody Encuesta nuevaEncuesta) {
        // Esto es necesario para que cada Pregunta "sepa" a qué Encuesta pertenece
        // antes de guardarla (si no, Hibernate no sabría cómo relacionarlas)
        if (nuevaEncuesta.getPreguntas() != null) {
            for (Pregunta p : nuevaEncuesta.getPreguntas()) {
                p.setEncuesta(nuevaEncuesta);
            }
        }
        return encuestaRepository.save(nuevaEncuesta);
    }

    // DELETE /api/encuestas/5 -> elimina una encuesta por su id
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        encuestaRepository.deleteById(id);
    }
}