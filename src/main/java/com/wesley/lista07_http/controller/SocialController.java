package com.wesley.lista07_http.controller;

import com.wesley.lista07_http.model.Tweet;
import com.wesley.lista07_http.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class SocialController {

    private List<Usuario> usuarios = new ArrayList<>();

    public SocialController() {

        usuarios.add(new Usuario("Wesley Machado", "wesley@email.com"));
    }


    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }


    @GetMapping("/{id}/tweets")
    public ResponseEntity<List<Tweet>> listarTweets(@PathVariable UUID id) {
        Usuario u = usuarios.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u.getTweets());
    }


    @PostMapping("/{id}/tweets")
    public ResponseEntity<Tweet> postar(@PathVariable UUID id, @RequestBody String mensagem) {
        Usuario u = usuarios.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (u == null) return ResponseEntity.notFound().build();

        Tweet novoTweet = new Tweet(mensagem);
        u.getTweets().add(novoTweet);
        return ResponseEntity.ok(novoTweet);
    }


    @PatchMapping("/{id}/tweets/{tweetId}")
    public ResponseEntity<Tweet> editar(@PathVariable UUID id, @PathVariable UUID tweetId, @RequestBody String novaMensagem) {
        Usuario u = usuarios.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (u == null) return ResponseEntity.notFound().build();

        Tweet t = u.getTweets().stream()
                .filter(tweet -> tweet.getTweetId().equals(tweetId))
                .findFirst()
                .orElse(null);

        if (t == null) return ResponseEntity.notFound().build();

        t.setMensagem(novaMensagem);
        t.setEditado(true);
        return ResponseEntity.ok(t);
    }


    @DeleteMapping("/{id}/tweets/{tweetId}")
    public ResponseEntity<Void> removerTweet(@PathVariable UUID id, @PathVariable UUID tweetId) {
        Usuario u = usuarios.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (u != null) {
            u.getTweets().removeIf(t -> t.getTweetId().equals(tweetId));
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}