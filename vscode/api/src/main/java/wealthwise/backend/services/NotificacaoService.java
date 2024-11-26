package wealthwise.backend.services;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.domain.Usuario;
import wealthwise.backend.repositories.NotificacaoRepository;

@Service
public class NotificacaoService extends BaseService <Notificacao, Long, NotificacaoRepository> {
    
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Notificacao getNotificacaoById(Long notificacaoID) {
        return notificacaoRepository.findById(notificacaoID)
                .orElseThrow(() -> new IllegalArgumentException("Notificacao not found with id - " + notificacaoID));
    }

    private Long getIdFromEntity(Notificacao notificacao) {
        try {
            Field idField = notificacao.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return (Long) idField.get(notificacao);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public Notificacao createNotificacao(Notificacao notificacao) {
        Long id = getIdFromEntity(notificacao);
    
        if (id != null && notificacaoRepository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");   
        
        Usuario usuario = usuarioService.getUserById(notificacao.getUsuario().getUsername());
        List<Notificacao> notificacoes = usuario.getNotificacoes();
        notificacoes.add(notificacao);
        usuario.setNotificacoes(notificacoes);

        return notificacaoRepository.save(notificacao);
    }

    public Notificacao updateNotificacao(Notificacao updatedNotificacao, Long notificacaoID){
        Optional<Notificacao> result = notificacaoRepository.findById(notificacaoID);
        
        if(result.isPresent()){
            Notificacao existingNotificacao = getNotificacaoById(notificacaoID);

            if(updatedNotificacao.getNotification_message() != null)
                existingNotificacao.setNotification_message(updatedNotificacao.getNotification_message());
            if (updatedNotificacao.getMessage_type() != null)
                existingNotificacao.setMessage_type(updatedNotificacao.getMessage_type());

            return notificacaoRepository.save(existingNotificacao);
        } else
            throw new IllegalArgumentException("Notificacao does not exist - " + updatedNotificacao.getId());
    }

    public void deleteNotificacao(Long id) {
        Usuario usuario = usuarioService.getUserById(getNotificacaoById(id).getUsuario().getUsername());
        
        if(Objects.nonNull(usuario)){
            List<Notificacao> notificacoes = usuario.getNotificacoes();

            for(Iterator<Notificacao> it = notificacoes.iterator(); it.hasNext();) {
                Notificacao notificacao = it.next();
                if(notificacao.getId().equals(id)){
                    notificacao.setUsuario(null);
                    it.remove();
                    break;
                }
            }
        }
        notificacaoRepository.deleteById(id);
    }
}
