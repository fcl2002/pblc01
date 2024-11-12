package wealthwise.backend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Variavel;
import wealthwise.backend.repositories.VariavelRepository;

@Service
public class VariavelService extends BaseService <Variavel, UUID, VariavelRepository> {
    public VariavelService(VariavelRepository repository) {
        super(repository);
    }
}
