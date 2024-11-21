package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.services.NotificacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/all")
    public ResponseEntity<List<Notificacao>> getAll() {
        List<Notificacao> notificacoes = notificacaoService.getAll();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> getNotificacao(@PathVariable Long id) {
        Notificacao notificacao = notificacaoService.getId(id).orElse(null);
        return ResponseEntity.ok(notificacao);
    }

    @PostMapping
    public ResponseEntity<Notificacao> postNotificacao(@RequestBody Notificacao notificacao) {
        Notificacao notification = notificacaoService.createNotificacao(notificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> putNotificacao(@RequestBody Notificacao notificacao, @PathVariable Long id) {
        Notificacao notification = notificacaoService.updateNotificacao(notificacao, id);
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotificacao(@PathVariable Long id) {
        notificacaoService.deleteId(id);
    }
}
