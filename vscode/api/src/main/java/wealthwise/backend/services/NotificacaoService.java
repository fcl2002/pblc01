package wealthwise.backend.services;

import java.util.Optional;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.repositories.NotificacaoRepository;

@Service
public class NotificacaoService extends BaseService <Notificacao, Long, NotificacaoRepository> {
    
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public Notificacao getNotificacaoById(Long userID) {

        return notificacaoRepository.findById(userID)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id - " + userID));
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
    
        return notificacaoRepository.save(notificacao);
    }

    public Notificacao updateNotificacao(Notificacao updatedNotificacao, Long notificacaoID) {
        
        Optional<Notificacao> result = notificacaoRepository.findById(notificacaoID);

        if(result.isPresent()) {
            Notificacao existingNotificacao = getNotificacaoById(notificacaoID);
    
            if (updatedNotificacao.getNotification_message() != null)
                existingNotificacao.setNotification_message(updatedNotificacao.getNotification_message());
            if (updatedNotificacao.getMessage_type() != null)
                existingNotificacao.setMessage_type(updatedNotificacao.getMessage_type());
    
            return notificacaoRepository.save(existingNotificacao);

        } else
            throw new IllegalArgumentException("Notificacao's id does not exist - " + updatedNotificacao.getId());
    }

    public void deleteNotificacao(Long userID) {

        Notificacao user = getNotificacaoById(userID);
        notificacaoRepository.delete(user);
    }
}
