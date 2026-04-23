package com.wesley.lista07_http.controller;

import com.wesley.lista07_http.model.Filme;
import com.wesley.lista07_http.model.Ingresso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
public class CinemaController {

    private List<Filme> filmes = new ArrayList<>();
    private List<Ingresso> ingressos = new ArrayList<>();

    // GET /filmes -> lista todos
    @GetMapping
    public List<Filme> listarFilmes() {
        return filmes;
    }


    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {

        Filme novoFilme = new Filme(filme.getTitulo(), "", filme.getCapacidade());
        filmes.add(novoFilme);
        return novoFilme;
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Filme> editarFilme(@PathVariable UUID id, @RequestBody Filme filmeDados) {
        Filme f = filmes.stream().filter(filme -> filme.getId().equals(id)).findFirst().orElse(null);
        if (f == null) return ResponseEntity.notFound().build();


        if (filmeDados.getTitulo() != null) {

        }
        return ResponseEntity.ok(f);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFilme(@PathVariable UUID id) {
        filmes.removeIf(f -> f.getId().equals(id));
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/ingressos")
    public ResponseEntity<Ingresso> comprarIngresso(@PathVariable UUID id, @RequestBody int assentoNumero) {
        Filme filme = filmes.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (filme == null) {
            return ResponseEntity.notFound().build();
        }


        if (filme.getAssentosOcupados() >= filme.getCapacidade()) {
            return ResponseEntity.status(400).build(); // Cinema lotado
        }


        filme.setAssentosOcupados(filme.getAssentosOcupados() + 1);


        Ingresso novoIngresso = new Ingresso(id, assentoNumero);
        ingressos.add(novoIngresso);

        return ResponseEntity.ok(novoIngresso);
    }


    @DeleteMapping("/{id}/ingressos/{ingressoId}")
    public ResponseEntity<String> devolverIngresso(@PathVariable UUID id, @PathVariable UUID ingressoId) {
        Ingresso ing = ingressos.stream()
                .filter(i -> i.getIngressoId().equals(ingressoId))
                .findFirst()
                .orElse(null);

        if (ing == null) return ResponseEntity.notFound().build();

        Filme filme = filmes.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        if (filme != null) {
            filme.setAssentosOcupados(filme.getAssentosOcupados() - 1);
        }

        ingressos.remove(ing);
        return ResponseEntity.ok("Ingresso devolvido!");
    }
}