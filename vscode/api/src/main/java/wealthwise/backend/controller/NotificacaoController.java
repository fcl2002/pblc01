package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.services.NotificacaoService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Notificacao> getAll() {
        return notificacaoService.getAll();
    }

    @GetMapping("/{id}")
    public Notificacao getNotificacao(@PathVariable Long id) {
        return notificacaoService.getId(id).orElse(null);
    }

    @PostMapping
    public Notificacao postNotificacao(@RequestBody Notificacao notificacao) {
        return notificacaoService.create(notificacao);
    }

    @PutMapping("/{id}")
    public Notificacao putNotificacao(@RequestBody Notificacao notificacao, @PathVariable Long id) {
        return notificacaoService.update(notificacao);
    }

    @DeleteMapping("/{id}")
    public void deleteNotificacao(@PathVariable Long id) {
        notificacaoService.deleteId(id);
    }
}
