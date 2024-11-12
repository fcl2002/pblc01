package wealthwise.backend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Fixo;
import wealthwise.backend.repositories.FixoRepository;

@Service
public class FixoService extends BaseService <Fixo, UUID, FixoRepository> {
    public FixoService(FixoRepository repository) {
        super(repository);
    }
}