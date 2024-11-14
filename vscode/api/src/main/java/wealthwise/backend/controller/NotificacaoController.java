// package wealthwise.backend.controller;

// import java.util.List;
// import java.util.UUID;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import wealthwise.backend.domain.Notificacao;
// import wealthwise.backend.services.NotificacaoService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;

// @RestController
// @RequestMapping("/notificacao")
// public class NotificacaoController {

//     @Autowired
//     private NotificacaoService notificacaoService;

//     // get localhost:8080/notificacao/all
//     @GetMapping("/all")
//     public List<Notificacao> getAll() {
//         return notificacaoService.getAll();
//     }

//     // get localhost:8080/notificacao/{string}
//     @GetMapping("/{string}")
//     public Notificacao getNotificacao(@PathVariable UUID id) {
//         return notificacaoService.getId(id).orElse(null);
//     }

//     // insert
//     // post localhost:8080/notificacao
//     @PostMapping
//     public Notificacao postNotificacao(@RequestBody Notificacao notificacao) {
//         return notificacaoService.create(notificacao);
//     }

//     // update
//     // put localhost:8080/notificacao/{string}
//     @PutMapping("/{string}")
//     public Notificacao putNotificacao(@RequestBody Notificacao notificacao, @PathVariable UUID uuid) {
//         return notificacaoService.update(notificacao);
//     }

//     // delete
//     // delete localhost:8080/notificacao/{string}
//     @DeleteMapping("/{string}")
//     public void deleteNotificacao(@PathVariable UUID id) {
//         notificacaoService.deleteId(id);
//     }
// }
