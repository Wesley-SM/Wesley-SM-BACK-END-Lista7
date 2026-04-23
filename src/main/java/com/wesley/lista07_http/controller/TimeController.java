package com.wesley.lista07_http.controller;

import com.wesley.lista07_http.model.Jogador;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {


    private List<Jogador> titulares = new ArrayList<>();
    private List<Jogador> reservas = new ArrayList<>();

    public TimeController() {

        for (int i = 0; i < 11; i++) {
            titulares.add(new Jogador("Titular " + i, "Posição", 20));
        }
        for (int i = 0; i < 5; i++) {
            reservas.add(new Jogador("Reserva " + i, "Reserva", 19));
        }
    }

    @GetMapping("/principal")
    public List<Jogador> getTitulares() {
        return titulares;
    }

    @GetMapping("/reservas")
    public List<Jogador> getReservas() {
        return reservas;
    }

    @PutMapping("/jogador/{posicao}")
    public ResponseEntity<String> substituir(@PathVariable int posicao, @RequestBody Jogador reserva) {
        if (posicao < 0 || posicao >= 11) {
            return ResponseEntity.badRequest().body("Posição titular inválida");
        }

        Jogador saindo = titulares.get(posicao);
        reservas.add(saindo);
        titulares.set(posicao, reserva);

        return ResponseEntity.ok("Troca realizada!");
    }
}