package wealthwise.backend.services;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Ativo;
import wealthwise.backend.domain.Cotacao;
import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.repositories.AtivoRepository;
import wealthwise.backend.repositories.CotacaoRepository;

@Service
public class CotacaoService extends BaseService <Cotacao, Long, CotacaoRepository> {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private AtivoService ativoService;

    public Cotacao getCotacaoById(Long cotacaoID) {
        
        return cotacaoRepository.findById(cotacaoID)
            .orElseThrow(() -> new IllegalArgumentException("Cotacao not found with id - " + cotacaoID));
    }

    private Long getIdFromEntity(Cotacao cotacao) {
        try {
            Field idField = cotacao.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return (Long) idField.get(cotacao);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public Cotacao createCotacao(Cotacao cotacao) {
        Long id = getIdFromEntity(cotacao);
    
        if (id != null && cotacaoRepository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");

        @SuppressWarnings("deprecation")
        Ativo ativo = ativoRepository.getById(cotacao.getAtivo().getId());

        List<Cotacao> cotacoes = ativo.getCotacoes();
        cotacoes.add(cotacao);
        ativo.setCotacoes(cotacoes);

    
        return cotacaoRepository.save(cotacao);
    }

    public void deleteCotacao(Long cotacaoID) {
        Ativo ativo = ativoService.getAtivoById(getCotacaoById(cotacaoID).getAtivo().getId());
        List<Notificacao> notificacoes = getCotacaoById(cotacaoID).getAtivo().getCarteira().getUsuario().getNotificacoes();
        
        if(Objects.nonNull(ativo)){
            List<Cotacao> cotacoes = ativo.getCotacoes();

            for(Iterator<Cotacao> it = cotacoes.iterator(); it.hasNext();) {
                Cotacao cotacao = it.next();
                if(cotacao.getId() == cotacaoID){
                    cotacao.setAtivo(null);
                    it.remove();
                    break;
                }
            }
        }

        if(Objects.nonNull(notificacoes)){
            for(Iterator<Notificacao> it = notificacoes.iterator(); it.hasNext();) {
                Notificacao notificacao = it.next();
                if(notificacao.getCotacao().getId() == cotacaoID){
                    notificacao.setCotacao(null);
                    it.remove();
                }
            }
        }
        
        cotacaoRepository.deleteById(cotacaoID);
    }
}