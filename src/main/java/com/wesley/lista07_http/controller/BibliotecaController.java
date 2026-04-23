package com.wesley.lista07_http.controller;

import com.wesley.lista07_http.model.Emprestimo;
import com.wesley.lista07_http.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    private List<Livro> disponiveis = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();


    public BibliotecaController() {
        disponiveis.add(new Livro("Java Como Programar", "Deitel"));
        disponiveis.add(new Livro("Clean Code", "Robert Martin"));
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return disponiveis;
    }

    @GetMapping("/emprestados")
    public List<Emprestimo> getEmprestados() {
        return emprestimos;
    }

    @PostMapping("/emprestados")
    public ResponseEntity<Emprestimo> emprestar(@RequestParam UUID livroId, @RequestParam String usuarioId) {
        // Busca o livro
        Livro livro = disponiveis.stream()
                .filter(l -> l.getId().equals(livroId))
                .findFirst()
                .orElse(null);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }


        disponiveis.remove(livro);
        Emprestimo novoEmprestimo = new Emprestimo(livroId, usuarioId);
        emprestimos.add(novoEmprestimo);

        return ResponseEntity.ok(novoEmprestimo);
    }

    @DeleteMapping("/emprestados/{emprestimoId}")
    public ResponseEntity<String> devolver(@PathVariable UUID emprestimoId) {
        Emprestimo e = emprestimos.stream()
                .filter(emp -> emp.getEmprestimoId().equals(emprestimoId))
                .findFirst()
                .orElse(null);

        if (e == null) {
            return ResponseEntity.notFound().build();
        }


        emprestimos.remove(e);
        return ResponseEntity.ok("Livro devolvido com sucesso!");
    }
}